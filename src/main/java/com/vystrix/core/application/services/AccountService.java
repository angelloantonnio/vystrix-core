package com.vystrix.core.application.services;

import com.vystrix.core.application.dto.AccountDTO;
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

    private Account buildAccount(User user){
        return new Account(
                BigDecimal.ZERO,
                CurrencyType.BRL,
                user
        );
    }
}
