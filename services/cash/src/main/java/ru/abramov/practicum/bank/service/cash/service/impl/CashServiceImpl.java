package ru.abramov.practicum.bank.service.cash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.abramov.practicum.bank.client.account.api.AccountApi;
import ru.abramov.practicum.bank.client.account.model.AccountDto;
import ru.abramov.practicum.bank.client.account.model.ChangeBalanceDto;
import ru.abramov.practicum.bank.common.exception.BadRequestException;
import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.service.cash.dto.CashTransactionDto;
import ru.abramov.practicum.bank.service.cash.service.CashService;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CashServiceImpl implements CashService {

    private final AccountApi accountApi;

    @Override
    public void putCash(CashTransactionDto transactionDto, User user) {

        AccountDto accountDto = getAccountByNumber(transactionDto.getAccountNumber(), user);

        ChangeBalanceDto changeBalanceDto = new ChangeBalanceDto();

        changeBalanceDto.setAmount(
                transactionDto.getAmount()
                        .add(accountDto.getBalance())
        );

        changeBalanceDto.setVersion(accountDto.getVersion());

        accountApi.changeBalance(accountDto.getId(), changeBalanceDto);
    }

    @Override
    public void withdrawCash(CashTransactionDto transactionDto, User user) {
        AccountDto accountDto = getAccountByNumber(transactionDto.getAccountNumber(), user);

        BigDecimal result = accountDto.getBalance().subtract(transactionDto.getAmount());

        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("Insufficient funds in the account");
        }

        ChangeBalanceDto changeBalanceDto = new ChangeBalanceDto();

        changeBalanceDto.setAmount(result);
        changeBalanceDto.setVersion(accountDto.getVersion());

        accountApi.changeBalance(accountDto.getId(), changeBalanceDto);
    }

    private AccountDto getAccountByNumber(String accountNumber, User user) {
        AccountDto accountDto = accountApi.getAccountByNumber(accountNumber);

        if (!Objects.equals(accountDto.getUserId(), user.getId())) {
            throw new AccessDeniedException("User is not owner of account %s".formatted(accountDto.getUserId()));
        }

        return accountDto;
    }
}
