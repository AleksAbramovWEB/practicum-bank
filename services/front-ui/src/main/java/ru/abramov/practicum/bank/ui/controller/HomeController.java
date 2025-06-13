package ru.abramov.practicum.bank.ui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.abramov.practicum.bank.client.account.api.AccountApi;
import ru.abramov.practicum.bank.client.account.model.AccountDto;
import ru.abramov.practicum.bank.ui.dto.PasswordUserFormDto;
import ru.abramov.practicum.bank.ui.dto.UserFormDto;
import ru.abramov.practicum.bank.common.annotation.CurrentUser;
import ru.abramov.practicum.bank.common.model.User;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AccountApi accountApi;

    @GetMapping("/")
    public String home(Model model, @CurrentUser User user) {

        model.addAttribute("user", user);
        model.addAttribute("userFrom", UserFormDto.of(user));
        model.addAttribute("passwordFrom", new PasswordUserFormDto());

        model.addAttribute("accounts", accountApi.getAccounts());
        model.addAttribute("account", new AccountDto());

        return "home";
    }
}
