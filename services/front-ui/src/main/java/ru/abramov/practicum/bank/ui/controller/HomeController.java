package ru.abramov.practicum.bank.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.abramov.practicum.bank.ui.dto.PasswordUserFormDto;
import ru.abramov.practicum.bank.ui.dto.UserFormDto;
import ru.abramov.practicum.bank.common.annotation.CurrentUser;
import ru.abramov.practicum.bank.common.model.User;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @CurrentUser User user) {

        model.addAttribute("user", user);
        model.addAttribute("userFrom", UserFormDto.of(user));
        model.addAttribute("passwordFrom", new PasswordUserFormDto());

        return "home";
    }
}
