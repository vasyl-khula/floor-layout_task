package com.nerdysoft.task.floor_layout.dto;

import com.nerdysoft.task.floor_layout.model.Point;
import com.nerdysoft.task.floor_layout.model.Room;

import java.util.List;
import java.util.Objects;

public class RoomDto {

    List<Point> room;

    public RoomDto() {
    }

    public RoomDto(List<Point> room) {
        this.room = room;
    }

    public List<Point> getRoom() {
        return room;
    }

    public static Room convertFromDto(RoomDto dto){
        Room room=new Room();
        room.setName("room");
        room.setRoom(dto.getRoom());
        return room;
    }

    public static RoomDto convertToDto(Room room){
        return new RoomDto(room.getRoom());
    }

    public void setRoom(List<Point> room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDto roomDto = (RoomDto) o;
        return Objects.equals(room, roomDto.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room);
    }
}
