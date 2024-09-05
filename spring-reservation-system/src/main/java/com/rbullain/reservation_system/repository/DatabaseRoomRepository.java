package com.rbullain.reservation_system.repository;

import com.rbullain.reservation_system.entity.Room;
import com.rbullain.reservation_system.model.RoomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DatabaseRoomRepository implements IRoomRepository {
    private final JpaRoomRepository jpaRepository;

    @Autowired
    public DatabaseRoomRepository(JpaRoomRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<RoomModel> findAll() {
        return jpaRepository
                .findAll()
                .stream()
                .map(RoomModel::fromEntity)
                .toList();
    }

    @Override
    public RoomModel create(RoomModel room) {
        Room newRoom = jpaRepository.save(new Room(
                null,
                room.getName(),
                room.getDescription())
        );
        return RoomModel.fromEntity(newRoom);
    }

    @Override
    public Optional<RoomModel> findById(Long id) {
        return jpaRepository
                .findById(id)
                .map(RoomModel::fromEntity);
    }

    @Override
    public void update(Long id, RoomModel room) {
        jpaRepository.save(new Room(
                id,
                room.getName(),
                room.getDescription()
        ));
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}

interface JpaRoomRepository extends JpaRepository<Room, Long> {
}