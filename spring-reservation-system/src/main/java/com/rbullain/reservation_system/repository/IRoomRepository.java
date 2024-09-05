package com.rbullain.reservation_system.repository;

import com.rbullain.reservation_system.model.RoomModel;

import java.util.List;
import java.util.Optional;

public interface IRoomRepository {
    List<RoomModel> findAll();

    Optional<RoomModel> findById(Long id);

    RoomModel create(RoomModel room);

    void update(Long id, RoomModel room);

    void delete(Long id);
}
