package com.vystrix.core.infrastructure.repositories;

import com.vystrix.core.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findTransactionByAccountId(UUID accountId);

    @Query("SELECT t FROM Transaction t WHERE t.account.user.email = :username")
    List<Transaction> findTransactionByUsername(@Param("username") String username);
}
