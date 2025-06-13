package ru.abramov.practicum.bank.ui.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.abramov.practicum.bank.client.account.api.AccountApi;
import ru.abramov.practicum.bank.client.account.model.AccountDto;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountApi accountApi;

    @PostMapping
    public String addAccount(@Valid @ModelAttribute("account") AccountDto accountDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formErrors", result.getAllErrors());

            return "error";
        }

        accountApi.addAccount(accountDto);

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountApi.deleteAccount(id);
        return "redirect:/";
    }
}
