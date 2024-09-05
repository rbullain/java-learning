package com.rbullain.reservation_system.controller;

import com.rbullain.reservation_system.dto.RoomRequestDTO;
import com.rbullain.reservation_system.dto.RoomResponseDTO;
import com.rbullain.reservation_system.exceptions.RoomNotFoundException;
import com.rbullain.reservation_system.model.RoomModel;
import com.rbullain.reservation_system.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    List<RoomResponseDTO> listRooms() {
        return roomService.listRooms();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RoomResponseDTO createRoom(@Valid @RequestBody RoomRequestDTO room) {
        return roomService.createRoom(room);
    }

    @GetMapping("/{id}")
    RoomResponseDTO getRoom(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateRoom(@PathVariable Long id, @Valid @RequestBody RoomRequestDTO room) {
        roomService.updateRoom(id, room);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
