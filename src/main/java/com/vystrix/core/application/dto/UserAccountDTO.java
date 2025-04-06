package com.vystrix.core.application.dto;

import com.vystrix.core.domain.enums.CurrencyType;

import java.math.BigDecimal;

public record UserAccountDTO(
        String name,
        String email,
        BigDecimal balance,
        CurrencyType currency
) {}
