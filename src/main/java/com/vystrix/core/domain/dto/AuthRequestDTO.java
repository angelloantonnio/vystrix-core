package com.vystrix.core.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank(message = "O campo email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "O campo senha é obrigatório")
        String password
) {
}
