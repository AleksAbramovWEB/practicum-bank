package ru.abramov.practicum.bank.service.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abramov.practicum.bank.service.exchange.model.Currency;
import ru.abramov.practicum.bank.service.exchange.model.ExchangeRate;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    Optional<ExchangeRate> findByCurrency(Currency currency);
}
