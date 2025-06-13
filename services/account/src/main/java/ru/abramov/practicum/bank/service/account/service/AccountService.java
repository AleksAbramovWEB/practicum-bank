package ru.abramov.practicum.bank.service.account.service;

import ru.abramov.practicum.bank.service.account.dto.AccountDto;
import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.service.account.model.Currency;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    List<AccountDto> getAccounts(String userId);

    AccountDto getAccount(String numberAccount);

    AccountDto openAccount(User user, Currency currency);

    void deleteAccount(User user, Long id);

    void blockAccount(Long id);

    void changeBalance(Long id, BigDecimal amount, Long version);
}
