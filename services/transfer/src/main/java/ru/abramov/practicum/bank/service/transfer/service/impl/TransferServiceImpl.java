package ru.abramov.practicum.bank.service.transfer.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.abramov.practicum.bank.client.account.api.AccountApi;
import ru.abramov.practicum.bank.client.account.model.AccountDto;
import ru.abramov.practicum.bank.client.account.model.ChangeBalanceDto;
import ru.abramov.practicum.bank.client.exchange.api.ExchangeApi;
import ru.abramov.practicum.bank.client.exchange.model.ConvertRequestDto;
import ru.abramov.practicum.bank.client.exchange.model.ConvertResponseDto;
import ru.abramov.practicum.bank.client.exchange.model.Currency;
import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.service.transfer.dto.TransferDto;
import ru.abramov.practicum.bank.service.transfer.service.TransferService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferServiceImpl implements TransferService {

    private final AccountApi accountApi;
    private final ExchangeApi exchangeApi;

    @Override
    @Retryable(
            include = { RuntimeException.class },
            maxAttempts = 4,
            backoff = @Backoff(delay = 500)
    )
    public void transfer(TransferDto transferDto, User user) {

        AccountDto accountFrom = accountApi.getAccountByNumber(transferDto.getFromAccount());
        if (!Objects.equals(accountFrom.getUserId(), user.getId())) {
            throw new AccessDeniedException("Only the owner can write off from this account");
        }
        if (accountFrom.getBalance().compareTo(transferDto.getAmount()) < 0) {
            throw new IllegalStateException("Insufficient funds on the account: " + transferDto.getFromAccount());
        }

        AccountDto accountTo = accountApi.getAccountByNumber(transferDto.getToAccount());

        ConvertRequestDto convertRequest = new ConvertRequestDto();
        convertRequest.setAmount(transferDto.getAmount());
        convertRequest.setFromCurrency(mapCurrency(accountFrom.getCurrency()));
        convertRequest.setToCurrency(mapCurrency(accountTo.getCurrency()));
        ConvertResponseDto converted = exchangeApi.convertCurrency(convertRequest);

        ChangeBalanceDto fromChange = new ChangeBalanceDto();
        fromChange.setAmount(accountFrom.getBalance().subtract(transferDto.getAmount()));
        fromChange.setVersion(accountFrom.getVersion());

        accountApi.changeBalance(accountFrom.getId(), fromChange);

        try {
            ChangeBalanceDto toChange = new ChangeBalanceDto();
            toChange.setAmount(accountTo.getBalance().add(converted.getResult()));
            toChange.setVersion(accountTo.getVersion());

            accountApi.changeBalance(accountTo.getId(), toChange);

        } catch (RuntimeException ex) {
            AccountDto updatedFrom = accountApi.getAccountByNumber(transferDto.getFromAccount());

            ChangeBalanceDto compensation = new ChangeBalanceDto();
            compensation.setAmount(updatedFrom.getBalance().add(transferDto.getAmount()));
            compensation.setVersion(updatedFrom.getVersion());

            accountApi.changeBalance(updatedFrom.getId(), compensation);

            throw new RuntimeException("Failed to credit account. Rolled back.", ex);
        }
    }

    @Recover
    public void recover(RuntimeException ex, TransferDto transferDto, User user) {
        log.error("Transfer permanently failed after retries: from={}, to={}, reason={}",
                transferDto.getFromAccount(),
                transferDto.getToAccount(),
                ex.getMessage());

        throw new IllegalStateException("Transfer failed and was rolled back. Manual intervention required.", ex);
    }

    private Currency mapCurrency(ru.abramov.practicum.bank.client.account.model.Currency currency) {
        return Currency.valueOf(currency.name());
    }
}


