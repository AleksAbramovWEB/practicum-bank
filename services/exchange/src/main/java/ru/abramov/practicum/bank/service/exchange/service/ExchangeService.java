package ru.abramov.practicum.bank.service.exchange.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import ru.abramov.practicum.bank.common.massage.Rate;
import ru.abramov.practicum.bank.service.exchange.dto.ConvertRequestDto;
import ru.abramov.practicum.bank.service.exchange.dto.ConvertResponseDto;
import ru.abramov.practicum.bank.service.exchange.dto.RateDto;

import java.util.List;

public interface ExchangeService {
    ConvertResponseDto convert(ConvertRequestDto convertRequestDto);

    void acceptRate(RateDto rateDto);

    void acceptRate(ConsumerRecord<String, Rate> record);

    List<RateDto> rates();
}
