package com.vystrix.core.domain.dto;

import com.vystrix.core.domain.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionRequestDTO(
        @NotNull @Positive
        BigDecimal amount,

        @NotNull
        TransactionType transactionType,

        String description
) {}
