package ru.abramov.practicum.bank.service.cash.service;

import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.service.cash.dto.CashTransactionDto;

public interface CashService {

    void putCash(CashTransactionDto transactionDto, User user);
    void withdrawCash(CashTransactionDto transactionDto, User user);
}
