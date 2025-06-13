package ru.abramov.practicum.bank.service.cash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.abramov.practicum.bank"})
public class CashServicePracticumBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashServicePracticumBankApplication.class, args);
    }

}
