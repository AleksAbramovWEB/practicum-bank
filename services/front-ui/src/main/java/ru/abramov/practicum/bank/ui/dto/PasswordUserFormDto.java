package ru.abramov.practicum.bank.ui.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ru.abramov.practicum.bank.ui.annotation.PasswordMatches;

@Data
@PasswordMatches
public class PasswordUserFormDto {

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}
