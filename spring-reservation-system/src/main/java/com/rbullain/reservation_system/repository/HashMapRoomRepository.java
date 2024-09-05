package com.rbullain.reservation_system.repository;

import com.rbullain.reservation_system.model.RoomModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("hashmap")
public class HashMapRoomRepository implements IRoomRepository {
    Map<Long, RoomModel> rooms = new HashMap<>();

    @Override
    public List<RoomModel> findAll() {
        return rooms.values().stream()
                .toList();
    }

    @Override
    public Optional<RoomModel> findById(Long id) {
        return Optional.ofNullable(rooms.get(id));
    }

    @Override
    public RoomModel create(RoomModel room) {
        room.setId((long) (rooms.size() + 1));

        rooms.put(room.getId(), room);
        return room;
    }

    @Override
    public void update(Long id, RoomModel room) {
        if (rooms.containsKey(id)) {
            RoomModel existingRoom = rooms.get(id);

            existingRoom.setName(room.getName());
            existingRoom.setDescription(room.getDescription());
        }
    }

    @Override
    public void delete(Long id) {
        rooms.remove(id);
    }
}
