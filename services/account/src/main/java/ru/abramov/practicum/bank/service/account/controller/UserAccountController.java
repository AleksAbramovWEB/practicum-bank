package ru.abramov.practicum.bank.service.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.abramov.practicum.bank.common.annotation.CurrentUser;
import ru.abramov.practicum.bank.common.dto.AccountDto;
import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.service.account.service.UserAccountService;

import java.util.List;

@RestController
@RequestMapping("account/user")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts(@CurrentUser User user) {
        return ResponseEntity.ok(userAccountService.getAccounts(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@CurrentUser User user, @PathVariable Long id) {
        return ResponseEntity.ok(userAccountService.getAccount(user, id));
    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@CurrentUser User user, @RequestBody AccountDto accountDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userAccountService.addAccount(user, accountDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@CurrentUser User user, @PathVariable Long id) {
        userAccountService.deleteAccount(user, id);

        return ResponseEntity.ok()
                .build();
    }
}
