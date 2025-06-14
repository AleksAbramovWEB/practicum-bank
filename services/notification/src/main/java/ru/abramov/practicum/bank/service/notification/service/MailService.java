package ru.abramov.practicum.bank.service.notification.service;

import ru.abramov.practicum.bank.service.notification.dto.MailDto;

public interface MailService {
    void send(MailDto mailDto);
}
