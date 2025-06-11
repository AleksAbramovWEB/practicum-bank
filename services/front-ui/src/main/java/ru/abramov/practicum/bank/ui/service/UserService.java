package ru.abramov.practicum.bank.ui.service;

import ru.abramov.practicum.bank.ui.dto.PasswordUserFormDto;
import ru.abramov.practicum.bank.ui.dto.UserFormDto;
import ru.abramov.practicum.bank.common.model.User;

public interface UserService {
    void edit(User user, UserFormDto userFormDto);
    void password(User user, PasswordUserFormDto passwordUserFormDto);
}
