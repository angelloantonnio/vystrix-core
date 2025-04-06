package com.vystrix.core.application.dto;

import java.math.BigDecimal;

public record TransactionSummaryDTO(
        BigDecimal totalCredit,
        BigDecimal totalDebit,
        BigDecimal netBalance
) {
}
