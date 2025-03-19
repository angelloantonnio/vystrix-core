package com.vystrix.core.application.dto;

import com.vystrix.core.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        Long id,
        Long accountId,
        BigDecimal amount,
        TransactionType transactionType,
        String description,
        LocalDateTime timestamp
) {}
