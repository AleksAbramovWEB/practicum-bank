package ru.abramov.practicum.bank.service.transfer.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import ru.abramov.practicum.bank.client.notification.api.NotificationApi;
import ru.abramov.practicum.bank.client.notification.model.MailDto;
import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.service.transfer.dto.TransferDto;
import ru.abramov.practicum.bank.service.transfer.service.NotificationService;

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
    public void notify(TransferDto transferDto, User user) {

        MailDto mailDto = new MailDto();

        mailDto.setEmail(user.getEmail());
        mailDto.setSubject("Перевод исполнен");
        mailDto.setText("Перевод с счета %s на счет %s исполнен".formatted(transferDto.getFromAccount(), transferDto.getToAccount()));

        notificationApi.sendMail(mailDto);
    }

    @Recover
    public void recover(RuntimeException ex, TransferDto transferDto, User user) {
        log.error("Notify failed after retries: from={}, to={}, reason={}, user={}",
                transferDto.getFromAccount(),
                transferDto.getToAccount(),
                ex.getMessage(),
                user
        );

        throw new IllegalStateException("Notify failed. Manual intervention required.", ex);
    }
}
