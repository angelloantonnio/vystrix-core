package com.vystrix.core.application.dto;

import com.vystrix.core.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionDTO(
        UUID id,
        UUID accountId,
        BigDecimal amount,
        TransactionType transactionType,
        String description,
        LocalDateTime timestamp
) {}
