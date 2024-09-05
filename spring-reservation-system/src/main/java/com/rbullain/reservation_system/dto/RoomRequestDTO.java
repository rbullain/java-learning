package com.rbullain.reservation_system.dto;

import jakarta.validation.constraints.NotEmpty;

public record RoomRequestDTO(
        @NotEmpty
        String name,
        String description
) {
}
