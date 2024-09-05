package com.rbullain.reservation_system.model;

import com.rbullain.reservation_system.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomModel {
    Long id;
    String name;
    String description;

    public RoomModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static RoomModel fromEntity(Room room) {
        return new RoomModel(room.getId(), room.getName(), room.getDescription());
    }
}
