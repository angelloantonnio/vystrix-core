package com.vystrix.core.application.services;

import com.vystrix.core.application.dto.AccountDTO;
import com.vystrix.core.application.exception.InsufficientBalanceException;
import com.vystrix.core.application.mapper.AccountMapper;
import com.vystrix.core.domain.entities.Account;
import com.vystrix.core.domain.entities.User;
import com.vystrix.core.domain.enums.CurrencyType;
import com.vystrix.core.infrastructure.repositories.AccountRepository;
import com.vystrix.core.infrastructure.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDTO getAccountById(UUID id){
        return accountRepository.findById(id)
                .map(accountMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada!"));
    }

    @Transactional
    protected AccountDTO createAccount(User user){
        Account userAccount = buildAccount(user);
        return accountMapper.toDTO(accountRepository.save(userAccount));
    }

    @Transactional
    protected void creditToAccount(Account account, BigDecimal amount){
        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    @Transactional
    protected void debitToAccount(Account account, BigDecimal amount){
        if(amount.compareTo(account.getBalance()) > 0) throw new InsufficientBalanceException();
        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    private Account buildAccount(User user){
        return new Account(
                BigDecimal.ZERO,
                CurrencyType.BRL,
                user
        );
    }
}
