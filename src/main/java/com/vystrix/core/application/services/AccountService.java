package com.vystrix.core.application.services;

import com.vystrix.core.application.dto.AccountDTO;
import com.vystrix.core.infrastructure.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountDTO getAccountById(Long id){
        return accountRepository.findById(id)
                .map(account -> new AccountDTO(account.getId(), account.getBalance(), account.getCurrency()))
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
    }
}
