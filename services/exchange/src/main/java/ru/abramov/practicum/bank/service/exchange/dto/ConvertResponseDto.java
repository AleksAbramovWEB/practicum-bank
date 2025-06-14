package ru.abramov.practicum.bank.service.exchange.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.abramov.practicum.bank.service.exchange.model.Currency;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ConvertResponseDto {

    @NotNull
    private BigDecimal result;
}
