package com.vystrix.core.application.services;

import com.vystrix.core.application.dto.TransactionResponseDTO;
import com.vystrix.core.application.dto.TransactionSummaryDTO;
import com.vystrix.core.application.mapper.TransactionMapper;
import com.vystrix.core.domain.dto.TransactionRequestDTO;
import com.vystrix.core.domain.entities.Account;
import com.vystrix.core.domain.entities.Transaction;
import com.vystrix.core.infrastructure.repositories.AccountRepository;
import com.vystrix.core.infrastructure.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.vystrix.core.domain.enums.TransactionType.CREDIT;
import static com.vystrix.core.domain.enums.TransactionType.DEBIT;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;
    private final AuthService authService;
    private final AccountService accountService;

    public List<TransactionResponseDTO> getTransactionByAccountId(UUID accountId){
        return transactionRepository.findTransactionByAccountId(accountId)
                .stream()
                .map(transactionMapper::toDTO)
                .toList();
    }

    public List<TransactionResponseDTO> getUserTransaction(){
        String username = authService.getAuthenticatedUsername();
        return transactionRepository.findTransactionByUsername(username)
                .stream()
                .map(transactionMapper::toDTO)
                .toList();
    }

    public TransactionSummaryDTO getUserTransactionSummary(){
        String username = authService.getAuthenticatedUsername();

        BigDecimal totalCreditTransactions = transactionRepository.sumCreditByUsername(username);
        BigDecimal totalDebitTransactions = transactionRepository.sumDebitByUsername(username);
        BigDecimal netBalance = getNetBalance(totalCreditTransactions, totalDebitTransactions);

        return new TransactionSummaryDTO(totalCreditTransactions, totalDebitTransactions, netBalance);
    }

    @Transactional
    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO){
        String username = authService.getAuthenticatedUsername();
        Account account = accountRepository.findAccountByUsername(username);

        balanceTransfer(account, transactionRequestDTO);

        Transaction transaction = transactionMapper.toEntity(transactionRequestDTO);
        transaction.setAccount(account);

        return transactionMapper.toDTO(transactionRepository.save(transaction));
    }

    private void balanceTransfer(Account account, TransactionRequestDTO transactionRequestDTO){
        if(transactionRequestDTO.transactionType().equals(CREDIT)){
            accountService.creditToAccount(account, transactionRequestDTO.amount());
        } else if(transactionRequestDTO.transactionType().equals(DEBIT)){
            accountService.debitToAccount(account, transactionRequestDTO.amount());
        }
    }

    private BigDecimal getNetBalance(BigDecimal credit, BigDecimal debit){
        return credit.subtract(debit);
    }
}
