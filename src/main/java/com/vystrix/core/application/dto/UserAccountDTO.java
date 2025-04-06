package com.vystrix.core.application.dto;

import com.vystrix.core.domain.enums.CurrencyType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserAccountDTO(
        UUID id,
        String name,
        String email,
        BigDecimal balance,
        CurrencyType currency
) {}
