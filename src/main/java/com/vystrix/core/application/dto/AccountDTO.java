package com.vystrix.core.application.dto;

import com.vystrix.core.domain.enums.CurrencyType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountDTO(
        BigDecimal balance,
        CurrencyType currency,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
