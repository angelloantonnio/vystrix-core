package com.vystrix.core.application.mapper;

import com.vystrix.core.application.dto.AccountDTO;
import com.vystrix.core.application.dto.UserAccountDTO;
import com.vystrix.core.application.dto.UserDTO;
import com.vystrix.core.domain.dto.UserCreateDTO;
import com.vystrix.core.domain.dto.UserUpdateDTO;
import com.vystrix.core.domain.entities.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "password", expression = "java(encodePassword(dto.password(), passwordEncoder))")
    User toEntity(UserCreateDTO dto, @Context PasswordEncoder passwordEncoder);

    UserDTO toDTO(User user);

    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "accountDTO.balance", target = "balance")
    @Mapping(source = "accountDTO.currency", target = "currency")
    UserAccountDTO toUserAccountDTO(User user, AccountDTO accountDTO);

    default String encodePassword(String password, PasswordEncoder passwordEncoder) {
        return password != null ? passwordEncoder.encode(password) : null;
    }
}
