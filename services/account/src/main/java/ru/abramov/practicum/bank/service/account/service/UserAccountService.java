package ru.abramov.practicum.bank.service.account.service;

import ru.abramov.practicum.bank.common.dto.AccountDto;
import ru.abramov.practicum.bank.common.model.User;

import java.util.List;

public interface UserAccountService {

    List<AccountDto> getAccounts(User user);

    AccountDto getAccount(User user, Long id);

    AccountDto addAccount(User user, AccountDto accountDto);

    void deleteAccount(User user, Long id);
}
