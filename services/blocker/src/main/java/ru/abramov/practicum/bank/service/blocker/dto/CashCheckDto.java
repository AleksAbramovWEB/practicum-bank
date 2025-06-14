package ru.abramov.practicum.bank.service.blocker.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashCheckDto {

    @NotEmpty
    private String accountNumber;

    @NotNull
    private BigDecimal amount;
}
