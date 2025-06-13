package ru.abramov.practicum.bank.ui.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.abramov.practicum.bank.client.cash.api.CashApi;
import ru.abramov.practicum.bank.client.cash.model.CashTransactionDto;

@Controller
@RequestMapping("/cash")
@RequiredArgsConstructor
public class CashController {

    private final CashApi cashApi;

    @PostMapping("/put")
    public String putCash(@Valid @ModelAttribute("cashTransaction") CashTransactionDto cashTransactionDto, BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formErrors", result.getAllErrors());

            return "error";
        }

        cashApi.putCash(cashTransactionDto);

        return "redirect:/";
    }

    @PostMapping("/withdraw")
    public String withdrawCash(@Valid @ModelAttribute("cashTransaction") CashTransactionDto cashTransactionDto, BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formErrors", result.getAllErrors());

            return "error";
        }

        cashApi.withdrawCash(cashTransactionDto);

        return "redirect:/";
    }
}
