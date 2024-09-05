package com.rbullain.reservation_system.dto;

import com.rbullain.reservation_system.model.RoomModel;

public record RoomResponseDTO(
        Long id,
        String name,
        String description
) {
    public static RoomResponseDTO fromModel(RoomModel room) {
        return new RoomResponseDTO(
                room.getId(),
                room.getName(),
                room.getDescription()
        );
    }
}
