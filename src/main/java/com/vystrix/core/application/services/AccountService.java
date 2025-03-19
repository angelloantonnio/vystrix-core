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

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDTO getAccountById(Long id){
        return accountRepository.findById(id)
                .map(account -> new AccountDTO(account.getId(), account.getBalance(), account.getCurrency(), account.getCreatedAt()))
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada!"));
    }

    @Transactional
    protected AccountDTO createAccount(User user){
        Account account = new Account();
        account.setBalance(BigDecimal.ZERO);
        account.setCurrency(CurrencyType.BRL);
        account.setUser(user);

        return accountMapper.toDTO(accountRepository.save(account));
    }
}
