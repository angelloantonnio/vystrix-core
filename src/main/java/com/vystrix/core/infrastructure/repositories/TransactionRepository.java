package com.vystrix.core.infrastructure.repositories;

import com.vystrix.core.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findTransactionByAccountId(UUID accountId);

    @Query("SELECT t FROM Transaction t WHERE t.account.user.email = :username")
    List<Transaction> findTransactionByUsername(@Param("username") String username);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.account.user.email = :username AND t.transactionType = 'CREDIT'")
    BigDecimal sumCreditByUsername(@Param("username") String username);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.account.user.email = :username AND t.transactionType = 'DEBIT'")
    BigDecimal sumDebitByUsername(@Param("username") String username);
}
