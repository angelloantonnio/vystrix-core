package com.vystrix.core.application.services;

import com.vystrix.core.application.dto.TransactionDTO;
import com.vystrix.core.application.mapper.TransactionMapper;
import com.vystrix.core.domain.dto.TransactionCreateDTO;
import com.vystrix.core.domain.entities.Account;
import com.vystrix.core.domain.entities.Transaction;
import com.vystrix.core.infrastructure.repositories.AccountRepository;
import com.vystrix.core.infrastructure.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    public List<TransactionDTO> getTransactionByAccountId(UUID accountId){
        return transactionRepository.findByAccountId(accountId)
                .stream()
                .map(transactionMapper::toDTO)
                .toList();
    }

    public TransactionDTO createTransaction(TransactionCreateDTO transactionCreateDTO) {
        Account account = accountRepository.findById(transactionCreateDTO.accountId())
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada!"));

        Transaction transaction = transactionMapper.toEntity(transactionCreateDTO);
        transaction.setAccount(account);

        return transactionMapper.toDTO(transactionRepository.save(transaction));
    }
}
