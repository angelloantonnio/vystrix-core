package com.vystrix.core.application.dto;

import com.vystrix.core.domain.enums.CurrencyType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountDTO(
        Long id,
        BigDecimal balance,
        CurrencyType currency,
        LocalDateTime createdAt
) {}
