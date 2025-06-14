package ru.abramov.practicum.bank.service.blocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.abramov.practicum.bank"})
public class BlockerServicePracticumBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockerServicePracticumBankApplication.class, args);
    }
}
