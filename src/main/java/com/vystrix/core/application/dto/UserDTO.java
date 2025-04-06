package com.vystrix.core.application.dto;

import java.time.LocalDateTime;

public record UserDTO(
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
