package com.nerdysoft.task.floor_layout.service.impl;

import com.nerdysoft.task.floor_layout.exception.RoomValidationException;
import com.nerdysoft.task.floor_layout.model.Point;
import com.nerdysoft.task.floor_layout.model.Room;
import com.nerdysoft.task.floor_layout.repository.RoomRepository;
import com.nerdysoft.task.floor_layout.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private RoomDataValidator roomDataValidator;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository,RoomDataValidator roomDataValidator) {
        this.roomRepository = roomRepository;
        this.roomDataValidator=roomDataValidator;
    }

    /**
     * if all room parameters are valid this Room is returned
     * @param room
     * return return valid room otherwise throw RoomValidationException
     */
    @Override
    public Room validateRoom(Room room){
        if(roomDataValidator.validate(room))
            return room;
        else
            throw new RoomValidationException("room data is not valid");
    }

    @Override
    public Room createRoom(Room room) {
        if(room!=null)
            return roomRepository.save(validateRoom(room));
        throw new IllegalArgumentException("can't create room");
    }

    @Override
    public Room getRoomByName(String name) {
        return roomRepository.findRoomByName(name)
                .orElseThrow(()->new NoSuchElementException("room with name "+name+" does not exist yet"));
    }

    @Override
    public Room updateRoom(Room room) {
        Room fromDb=getRoomByName(room.getName());
        if(fromDb!=null){
            return roomRepository.save(room);
        }
        throw new IllegalArgumentException("room can not be null");
    }

    @Override
    public boolean deleteRoomByName(String name) {
        Room room=roomRepository.findRoomByName(name).orElse(null);
        if(room!=null){
            roomRepository.delete(room);
            return true;
        }
        return false;
    }

    @Override
    public List<Point> getRoomCorners(String roomName) {
        return null;
    }
}
