package com.vystrix.core.application.dto;

import com.vystrix.core.domain.enums.TransactionType;

import java.math.BigDecimal;

public record TransactionResponseDTO(
        BigDecimal amount,
        TransactionType transactionType,
        String description
) {}
