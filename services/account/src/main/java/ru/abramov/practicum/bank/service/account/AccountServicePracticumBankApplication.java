package ru.abramov.practicum.bank.service.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "ru.abramov.practicum.bank")
@EntityScan(basePackages = "ru.abramov.practicum.bank")
@SpringBootApplication(scanBasePackages = "ru.abramov.practicum.bank")
public class AccountServicePracticumBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServicePracticumBankApplication.class, args);
    }
}
