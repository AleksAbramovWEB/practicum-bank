package ru.abramov.practicum.bank.service.blocker.service;

import ru.abramov.practicum.bank.service.blocker.dto.CashCheckDto;
import ru.abramov.practicum.bank.service.blocker.dto.ResultCheckDto;
import ru.abramov.practicum.bank.service.blocker.dto.TransferCheckDto;

public interface CheckService {

    ResultCheckDto check(CashCheckDto cashCheckDto);
    ResultCheckDto check(TransferCheckDto transferCheckDto);
}
