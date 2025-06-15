package ru.abramov.practicum.bank.service.exchange.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.abramov.practicum.bank.service.exchange.config.JwtTestConfig;
import ru.abramov.practicum.bank.service.exchange.dto.ConvertRequestDto;
import ru.abramov.practicum.bank.service.exchange.dto.RateDto;
import ru.abramov.practicum.bank.service.exchange.model.Currency;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(JwtTestConfig.class)
class ExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_TOKEN = "Bearer mock.jwt.token";

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM exchange_rate");
        jdbcTemplate.execute("INSERT INTO exchange_rate (id, currency, value, created, updated) " +
                "VALUES (1, 'USD', 100.00, NOW(), NOW())," +
                "(2, 'EUR', 120.00, NOW(), NOW())");
    }

    @AfterEach
    void clean() {
        jdbcTemplate.execute("DELETE FROM exchange_rate");
    }

    @Test
    void convert_ShouldReturnConvertedAmount() throws Exception {
        ConvertRequestDto dto = new ConvertRequestDto();
        dto.setFromCurrency(Currency.USD);
        dto.setToCurrency(Currency.EUR);
        dto.setAmount(new BigDecimal("2.00"));

        mockMvc.perform(post("/convert")
                        .header(AUTH_HEADER, BEARER_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").exists());
    }

    @Test
    void acceptRate_ShouldSaveNewRate() throws Exception {
        RateDto dto = new RateDto(Currency.USD, new BigDecimal("111.11"));

        mockMvc.perform(post("/accept")
                        .header(AUTH_HEADER, BEARER_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void rates_ShouldReturnAvailableRates() throws Exception {
        mockMvc.perform(get("/rates")
                        .header(AUTH_HEADER, BEARER_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].currency").exists())
                .andExpect(jsonPath("$[0].value").exists());
    }

    @Test
    void convert_ShouldReturnSameAmount_WhenSameCurrency() throws Exception {
        ConvertRequestDto dto = new ConvertRequestDto();
        dto.setFromCurrency(Currency.USD);
        dto.setToCurrency(Currency.USD);
        dto.setAmount(new BigDecimal("123.45"));

        mockMvc.perform(post("/convert")
                        .header(AUTH_HEADER, BEARER_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("123.45"));
    }
}

