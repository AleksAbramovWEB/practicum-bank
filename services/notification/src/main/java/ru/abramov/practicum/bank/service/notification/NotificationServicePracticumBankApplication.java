package ru.abramov.practicum.bank.service.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.abramov.practicum.bank"})
public class NotificationServicePracticumBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServicePracticumBankApplication.class, args);
    }

}
