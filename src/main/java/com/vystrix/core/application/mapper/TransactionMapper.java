package com.vystrix.core.application.mapper;

import com.vystrix.core.application.dto.TransactionDTO;
import com.vystrix.core.domain.dto.TransactionCreateDTO;
import com.vystrix.core.domain.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    Transaction toEntity(TransactionCreateDTO dto);

    @Mapping(source = "account.id", target = "accountId")
    TransactionDTO toDTO(Transaction transaction);
}
