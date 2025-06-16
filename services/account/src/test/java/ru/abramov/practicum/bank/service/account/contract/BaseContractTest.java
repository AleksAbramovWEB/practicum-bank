package ru.abramov.practicum.bank.service.account.contract;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;
import ru.abramov.practicum.bank.service.account.AccountServicePracticumBankApplication;
import ru.abramov.practicum.bank.service.account.model.AccountStatus;
import ru.abramov.practicum.bank.service.account.model.Currency;
import ru.abramov.practicum.bank.service.account.service.AccountService;
import ru.abramov.practicum.bank.service.account.service.impl.AccountServiceImpl;
import ru.abramov.practicum.bank.service.account.dto.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {AccountServicePracticumBankApplication.class, BaseContractTest.TestContractConfiguration.class})
@ActiveProfiles("test")
public abstract class BaseContractTest {

    @Autowired
    protected WebApplicationContext context;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);

        Jwt jwt = Jwt.withTokenValue("test-token")
                .header("alg", "none")
                .claim("sub", "user-456")
                .claim("preferred_username", "testuser")
                .claim("email", "test@example.com")
                .claim("given_name", "Test")
                .claim("family_name", "User")
                .claim("birth_date", "1990-01-01")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(jwt, jwt.getTokenValue(), List.of());

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @TestConfiguration
    static class TestContractConfiguration {

        @Bean
        @Primary
        public AccountService accountService() {
            AccountService mock = mock(AccountServiceImpl.class);

            doNothing().when(mock).blockAccount(1234L);

            doNothing().when(mock).changeBalance(eq(1234L), any(BigDecimal.class), eq(2L));

            when(mock.getAccounts("user-456")).thenReturn(List.of(
                    AccountDto.builder()
                            .id(1002L)
                            .number("40817810099910004322")
                            .userId("user-456")
                            .balance(BigDecimal.valueOf(5000.00))
                            .status(AccountStatus.BLOCKED)
                            .currency(Currency.USD)
                            .version(3L)
                            .build()
            ));

            when(mock.getAccounts("user-456")).thenReturn(List.of(
                    AccountDto.builder()
                            .id(1001L)
                            .number("40817810099910004321")
                            .userId("user-456")
                            .balance(BigDecimal.valueOf(5000.00))
                            .status(AccountStatus.ACTIVE)
                            .currency(Currency.RUB)
                            .version(5L)
                            .build()
            ));

            when(mock.getAccount("40817810099910004321")).thenReturn(
                    AccountDto.builder()
                            .id(1003L)
                            .number("40817810099910004321")
                            .userId("user-456")
                            .balance(BigDecimal.valueOf(5000.00))
                            .status(AccountStatus.ACTIVE)
                            .currency(Currency.EUR)
                            .version(7L)
                            .build()
            );


            when(mock.openAccount(any(), eq(Currency.RUB))).thenReturn(
                    AccountDto.builder()
                            .id(999L)
                            .number("40817810000000000001")
                            .userId("user-456")
                            .balance(BigDecimal.valueOf(5000.00))
                            .status(AccountStatus.ACTIVE)
                            .currency(Currency.RUB)
                            .version(1L)
                            .build()
            );

            doNothing().when(mock).deleteAccount(any(), eq(123L));

            return mock;
        }
    }
}
