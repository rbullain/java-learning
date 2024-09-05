package com.rbullain.reservation_system.dataloaders;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbullain.reservation_system.model.RoomModel;
import com.rbullain.reservation_system.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class RoomJsonDataLoader implements CommandLineRunner {
    private final IRoomRepository roomRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public RoomJsonDataLoader(@Qualifier("hashMapRoomRepository") IRoomRepository roomRepository, ObjectMapper objectMapper) {
        this.roomRepository = roomRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) {
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/initial-rooms.json")) {
            RoomsModels rooms = objectMapper.readValue(inputStream, RoomsModels.class);
            for (RoomModel room : rooms.rooms()) {
                roomRepository.create(room);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data from file", e);
        }
    }
}

record RoomsModels(
        List<RoomModel> rooms
) {
}