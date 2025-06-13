package ru.abramov.practicum.bank.service.account.dto;

import lombok.Data;
import ru.abramov.practicum.bank.service.account.model.AccountStatus;
import ru.abramov.practicum.bank.service.account.model.Currency;

import java.math.BigDecimal;

@Data
public class AccountDto {

    private Long id;

    private String number;

    private String userId;

    private BigDecimal balance;

    private AccountStatus status;

    private Currency currency;
}
