package com.vystrix.core.application.mapper;

import com.vystrix.core.application.dto.TransactionResponseDTO;
import com.vystrix.core.domain.dto.TransactionRequestDTO;
import com.vystrix.core.domain.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    Transaction toEntity(TransactionRequestDTO dto);

    TransactionResponseDTO toDTO(Transaction transaction);
}
