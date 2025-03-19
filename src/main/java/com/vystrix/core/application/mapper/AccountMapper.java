package com.vystrix.core.application.mapper;

import com.vystrix.core.application.dto.AccountDTO;
import com.vystrix.core.domain.entities.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO toDTO(Account account);
}
