package com.vystrix.core.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(
        @NotBlank
        String name,

        @Email @NotBlank
        String email,

        @NotBlank @Size(min = 8, message = "Senha deve conter no mínimo 8 caracteres")
        String password
) {}
