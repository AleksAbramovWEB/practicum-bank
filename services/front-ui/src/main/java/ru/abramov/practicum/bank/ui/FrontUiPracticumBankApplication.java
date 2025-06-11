package ru.abramov.practicum.bank.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.abramov.practicum.bank"})
public class FrontUiPracticumBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontUiPracticumBankApplication.class, args);
    }

}
