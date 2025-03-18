package com.vystrix.core.adapters.controllers;

import com.vystrix.core.application.dto.TransactionDTO;
import com.vystrix.core.application.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionByAccountId(@PathVariable Long accountId){
        return ResponseEntity.ok(transactionService.getTransactionByAccountId(accountId));
    }
}
