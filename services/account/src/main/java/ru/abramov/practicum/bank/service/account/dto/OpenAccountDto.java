package ru.abramov.practicum.bank.service.account.dto;

import lombok.Data;
import ru.abramov.practicum.bank.service.account.model.Currency;

@Data
public class OpenAccountDto {
    private Currency currency;
}
