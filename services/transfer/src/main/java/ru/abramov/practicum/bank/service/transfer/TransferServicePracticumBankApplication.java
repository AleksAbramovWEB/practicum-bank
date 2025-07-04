package ru.abramov.practicum.bank.service.transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.abramov.practicum.bank")
public class TransferServicePracticumBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransferServicePracticumBankApplication.class, args);
    }
}
