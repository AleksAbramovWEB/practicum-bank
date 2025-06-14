package ru.abramov.practicum.bank.ui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.abramov.practicum.bank.client.account.api.AccountApi;
import ru.abramov.practicum.bank.client.account.model.OpenAccountDto;
import ru.abramov.practicum.bank.client.cash.model.CashTransactionDto;
import ru.abramov.practicum.bank.client.transfer.model.TransferDto;
import ru.abramov.practicum.bank.ui.dto.PasswordUserFormDto;
import ru.abramov.practicum.bank.ui.dto.UserFormDto;
import ru.abramov.practicum.bank.common.annotation.CurrentUser;
import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.ui.service.UserService;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AccountApi accountApi;
    private final UserService userService;

    @GetMapping("/")
    public String home(Model model, @CurrentUser User user) {

        model.addAttribute("user", user);
        model.addAttribute("userFrom", UserFormDto.of(user));
        model.addAttribute("passwordFrom", new PasswordUserFormDto());

        model.addAttribute("accounts", accountApi.getCurrentUserAccounts());
        model.addAttribute("users", userService.getAllOverUsers(user));
        model.addAttribute("account", new OpenAccountDto());
        model.addAttribute("cashTransaction", new CashTransactionDto());
        model.addAttribute("transfer", new TransferDto());

        return "home";
    }
}
