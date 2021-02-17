package com.nerdysoft.task.floor_layout.service;

import com.nerdysoft.task.floor_layout.model.Point;
import com.nerdysoft.task.floor_layout.model.Room;

import java.util.List;

public interface RoomService {
    Room createRoom(Room room);
    Room getRoomByName(String name);
    Room updateRoom(Room room);
    boolean deleteRoomByName(String name);
    List<Point> getRoomCorners(String roomName);

    Room validateRoom(Room room);
}
