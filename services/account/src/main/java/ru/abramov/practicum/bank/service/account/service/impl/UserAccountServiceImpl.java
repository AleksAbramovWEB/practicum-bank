package ru.abramov.practicum.bank.service.account.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abramov.practicum.bank.common.dto.AccountDto;
import ru.abramov.practicum.bank.common.exception.BadRequestException;
import ru.abramov.practicum.bank.common.exception.NotFoundException;
import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.service.account.mapper.AccountMapper;
import ru.abramov.practicum.bank.service.account.model.Account;
import ru.abramov.practicum.bank.service.account.repostory.AccountRepository;
import ru.abramov.practicum.bank.service.account.service.UserAccountService;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public List<AccountDto> getAccounts(User user) {
        return accountRepository.getAllByUserId(user.getId())
                .stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccount(User user, Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found by id: %s".formatted(id)));

        if (!account.getUserId().equals(user.getId())) {
            throw new AccessDeniedException("Access denied to account by id: %s".formatted(id));
        }

        return accountMapper.toDto(account);
    }

    @Override
    @Transactional
    public AccountDto addAccount(User user, AccountDto accountDto) {

        Account account = new Account();

        if (accountRepository.existsAccountByNumber(accountDto.getNumber())) {
            throw new BadRequestException("Account number already exists");
        }

        if (accountRepository.existsByCurrencyAndUserId(accountDto.getCurrency(), accountDto.getUserId())) {
            throw new BadRequestException("Currency already exists for user");
        }

        account.setUserId(user.getId());
        account.setBalance(accountDto.getBalance());
        account.setCurrency(accountDto.getCurrency());
        account.setNumber(accountDto.getNumber());

        account = accountRepository.save(account);

        return accountMapper.toDto(account);
    }

    @Override
    public void deleteAccount(User user, Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found by id: %s".formatted(id)));

        if (!account.getUserId().equals(user.getId())) {
            throw new AccessDeniedException("Access denied to account by id: %s".formatted(id));
        }

        accountRepository.delete(account);
    }
}
