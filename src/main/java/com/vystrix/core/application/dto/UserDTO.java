package com.vystrix.core.application.dto;

import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt
) {}
