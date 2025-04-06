package com.vystrix.core.adapters.controllers;

import com.vystrix.core.application.dto.TransactionResponseDTO;
import com.vystrix.core.application.services.TransactionService;
import com.vystrix.core.domain.dto.TransactionRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/account/id/{accountId}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionByAccountId(@PathVariable UUID accountId){
        return ResponseEntity.ok(transactionService.getTransactionByAccountId(accountId));
    }

    @GetMapping("/me")
    public ResponseEntity<List<TransactionResponseDTO>> getUserTransaction(){
        return ResponseEntity.ok(transactionService.getUserTransaction());
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(transactionRequestDTO));
    }
}
