package com.vystrix.core.domain.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        @Nullable @Size(min = 1, message = "Nome não pode estar em branco")
        String name,

        @Nullable @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
        String password
) {
}