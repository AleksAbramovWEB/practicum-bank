package ru.abramov.practicum.bank.common.model;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ACTIVE("Активен"),
    BLOCKED("Заблокирован"),
    CLOSED("Закрыт");

    private final String title;

    AccountStatus(String title) {
        this.title = title;
    }
}
