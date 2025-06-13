package ru.abramov.practicum.bank.ui.config;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.abramov.practicum.bank.client.account.api.AccountApi;

@Configuration
public class AccountClientConfiguration {

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }

    @Bean
    public AccountApi accountApiClient(Feign.Builder feignBuilder,
                                       AccountFeignSecurityConfig authConfig,
                                       @Value("${api.account}") String baseUrl) {

        return feignBuilder
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .requestInterceptor(authConfig.userTokenRelayInterceptor())
                .logger(new feign.slf4j.Slf4jLogger(AccountApi.class))
                .logLevel(Logger.Level.FULL)
                .target(AccountApi.class, baseUrl);
    }
}
