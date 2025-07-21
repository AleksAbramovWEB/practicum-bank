package ru.abramov.practicum.bank.service.account.service;

import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.service.account.dto.AccountDto;

public interface NotificationService {
    void openAccount(AccountDto accountDto, User user);
}
