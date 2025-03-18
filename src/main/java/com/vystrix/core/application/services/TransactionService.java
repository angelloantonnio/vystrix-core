package com.vystrix.core.application.services;

import com.vystrix.core.application.dto.TransactionDTO;
import com.vystrix.core.infrastructure.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<TransactionDTO> getTransactionByAccountId(Long accountId){
        return transactionRepository.findByAccountId(accountId)
                .stream()
                .map(transaction -> new TransactionDTO(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getTransactionType(),
                        transaction.getDescription(),
                        transaction.getTimestamp()
                ))
                .toList();
    }
}
