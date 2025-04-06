package com.vystrix.core.infrastructure.repositories;

import com.vystrix.core.domain.entities.Account;
import com.vystrix.core.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("SELECT a FROM Account a WHERE a.user.email= :username")
    Account findAccountByUsername(@Param("username") String username);
}
