package com.rbullain.reservation_system.service;

import com.rbullain.reservation_system.dto.RoomRequestDTO;
import com.rbullain.reservation_system.dto.RoomResponseDTO;
import com.rbullain.reservation_system.exceptions.RoomNotFoundException;
import com.rbullain.reservation_system.model.RoomModel;
import com.rbullain.reservation_system.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final IRoomRepository roomRepository;

    @Autowired
    public RoomService(@Qualifier("hashMapRoomRepository") IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomResponseDTO> listRooms() {
        return roomRepository
                .findAll()
                .stream()
                .map(RoomResponseDTO::fromModel)
                .toList();
    }

    public RoomResponseDTO createRoom(RoomRequestDTO room) {
        return RoomResponseDTO.fromModel(
                roomRepository.create(new RoomModel(
                        room.name(),
                        room.description()
                ))
        );
    }

    public RoomResponseDTO getRoomById(Long id) {
        Optional<RoomModel> room = roomRepository.findById(id);
        if (room.isEmpty()) {
            throw new RoomNotFoundException();
        }
        return RoomResponseDTO.fromModel(room.get());
    }

    public void updateRoom(Long id, RoomRequestDTO room) {
        roomRepository.update(id, new RoomModel(
                room.name(),
                room.description()
        ));
    }

    public void deleteRoom(Long id) {
        roomRepository.delete(id);
    }
}
