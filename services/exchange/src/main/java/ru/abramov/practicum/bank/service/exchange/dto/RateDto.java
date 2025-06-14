package ru.abramov.practicum.bank.service.exchange.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.abramov.practicum.bank.service.exchange.model.Currency;
import ru.abramov.practicum.bank.service.exchange.model.ExchangeRate;

import java.math.BigDecimal;

@Data
public class RateDto {

    @NotNull
    private Currency currency;

    @NotNull
    private BigDecimal value;

    public static RateDto of(ExchangeRate rate) {
        RateDto dto = new RateDto();

        dto.currency = rate.getCurrency();
        dto.value = rate.getValue();

        return dto;
    }
}
