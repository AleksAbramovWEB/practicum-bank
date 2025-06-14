package ru.abramov.practicum.bank.service.cash.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import ru.abramov.practicum.bank.client.notification.api.NotificationApi;
import ru.abramov.practicum.bank.client.notification.model.MailDto;
import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.service.cash.dto.CashTransactionDto;
import ru.abramov.practicum.bank.service.cash.service.NotificationService;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationApi notificationApi;

    @Override
    @Retryable(
            include = { RuntimeException.class },
            maxAttempts = 2,
            backoff = @Backoff(delay = 100)
    )
    public void notifyPutCash(CashTransactionDto transferDto, User user) {
        MailDto mailDto = new MailDto();

        mailDto.setEmail(user.getEmail());
        mailDto.setSubject("Пополнение исполнено");
        mailDto.setText("Пополнение счета %s исполнено".formatted(transferDto.getAccountNumber()));

        notificationApi.sendMail(mailDto);
    }

    @Override
    @Retryable(
            include = { RuntimeException.class },
            maxAttempts = 2,
            backoff = @Backoff(delay = 100)
    )
    public void notifyWithdrawCash(CashTransactionDto transferDto, User user) {
        MailDto mailDto = new MailDto();

        mailDto.setEmail(user.getEmail());
        mailDto.setSubject("Вывод средств исполнено");
        mailDto.setText("Вывод средств исполнено со счета %s".formatted(transferDto.getAccountNumber()));

        notificationApi.sendMail(mailDto);
    }

    @Recover
    public void recover(RuntimeException ex, CashTransactionDto transferDto, User user) {
        log.error("Notify failed after retries: account={}, reason={}, user={}",
                transferDto.getAccountNumber(),
                ex.getMessage(),
                user
        );

        throw new IllegalStateException("Notify failed. Manual intervention required.", ex);
    }
}
