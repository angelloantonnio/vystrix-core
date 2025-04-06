package com.vystrix.core.adapters.controllers;

import com.vystrix.core.application.dto.AccountDTO;
import com.vystrix.core.application.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/id/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable UUID id){
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<AccountDTO> getAccountDetails(){
        return ResponseEntity.ok(accountService.getUserAccount());
    }
}
