package ru.abramov.practicum.bank.ui.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.abramov.practicum.bank.client.transfer.api.TransferApi;
import ru.abramov.practicum.bank.client.transfer.model.TransferDto;

@Controller
@RequiredArgsConstructor
public class TransferController {

    private final TransferApi transferApi;

    @PostMapping("/transfer")
    public String transfer(@Valid @ModelAttribute("transfer") TransferDto transferDto, BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formErrors", result.getAllErrors());

            return "error";
        }

        transferApi.transfer(transferDto);

        return "redirect:/";
    }
}
