package ru.abramov.practicum.bank.ui.service;

import ru.abramov.practicum.bank.ui.dto.PasswordUserFormDto;
import ru.abramov.practicum.bank.ui.dto.UserFormDto;
import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.ui.dto.UserItemDto;

import java.util.List;

public interface UserService {
    void edit(User user, UserFormDto userFormDto);
    void password(User user, PasswordUserFormDto passwordUserFormDto);
    List<UserItemDto> getAllOverUsers(User user);
}
