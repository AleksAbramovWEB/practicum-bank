package ru.abramov.practicum.bank.ui.service;

import ru.abramov.practicum.bank.ui.dto.UserFormDto;
import ru.abramov.practicum.bank.user.model.User;

public interface UserService {
    void edit(User user, UserFormDto userFormDto);
}
