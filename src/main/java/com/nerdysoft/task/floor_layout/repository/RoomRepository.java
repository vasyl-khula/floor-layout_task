package com.nerdysoft.task.floor_layout.repository;

import com.nerdysoft.task.floor_layout.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findRoomByName(String name);
}
