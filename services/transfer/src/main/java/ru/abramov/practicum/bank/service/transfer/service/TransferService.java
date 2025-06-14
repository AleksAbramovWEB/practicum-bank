package ru.abramov.practicum.bank.service.transfer.service;

import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.service.transfer.dto.TransferDto;

public interface TransferService {
    void transfer(TransferDto transferDto, User user);
}
