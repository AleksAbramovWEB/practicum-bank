package ru.abramov.practicum.bank.service.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.abramov.practicum.bank.common.annotation.CurrentUser;
import ru.abramov.practicum.bank.service.account.dto.AccountDto;
import ru.abramov.practicum.bank.common.model.User;
import ru.abramov.practicum.bank.service.account.dto.OpenAccountDto;
import ru.abramov.practicum.bank.service.account.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAccountController {

    private final AccountService userAccountService;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts(@CurrentUser User user) {
        return ResponseEntity.ok(userAccountService.getAccounts(user.getId()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<AccountDto>> getAccounts(@PathVariable String userId) {
        return ResponseEntity.ok(userAccountService.getAccounts(userId));
    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@CurrentUser User user, @RequestBody OpenAccountDto openAccountDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userAccountService.openAccount(user, openAccountDto.getCurrency()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@CurrentUser User user, @PathVariable Long id) {
        userAccountService.deleteAccount(user, id);

        return ResponseEntity.ok()
                .build();
    }
}
