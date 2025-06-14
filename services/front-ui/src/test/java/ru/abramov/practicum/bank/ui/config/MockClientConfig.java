package ru.abramov.practicum.bank.ui.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.abramov.practicum.bank.client.account.api.AccountClient;
import ru.abramov.practicum.bank.client.cash.api.CashClient;
import ru.abramov.practicum.bank.client.transfer.api.TransferClient;

@TestConfiguration
public class MockClientConfig {

    @Bean
    public AccountClient accountClient() {
        return Mockito.mock(AccountClient.class);
    }

    @Bean
    public CashClient cashClient() {
        return Mockito.mock(CashClient.class);
    }

    @Bean
    public TransferClient transferClient() {
        return Mockito.mock(TransferClient.class);
    }
}