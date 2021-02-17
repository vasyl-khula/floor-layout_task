package com.nerdysoft.task.floor_layout.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerdysoft.task.floor_layout.dto.RoomDto;
import com.nerdysoft.task.floor_layout.model.Room;
import com.nerdysoft.task.floor_layout.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomController {

    private RoomService roomService;
    private ObjectMapper objectMapper;

    @Autowired
    public RoomController(RoomService roomService,ObjectMapper objectMapper) {
        this.roomService = roomService;
        this.objectMapper=objectMapper;
    }

    @GetMapping("/validateRoom")
    public String validationPage(){
       return "create-room";
    }

    @PostMapping("/validateRoom")
    @ResponseBody
    public RoomDto doValidation(@RequestBody RoomDto roomDto) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        String str=mapper.writeValueAsString(roomDto);
        System.out.println(" room= "+str);

        Room room=RoomDto.convertFromDto(roomDto);
        room=roomService.createRoom(room);
        return RoomDto.convertToDto(room);
    }

    @GetMapping("/demo")
    public String demo(){
        return "demo-page";
    }

    @PostMapping("/demo")
    @ResponseBody
    public RoomDto doValidationDemo(@RequestBody RoomDto roomDto) throws JsonProcessingException {
        Room room=RoomDto.convertFromDto(roomDto);
       // room=roomService.createRoom(room);
        ObjectMapper mapper=new ObjectMapper();
        String str=mapper.writeValueAsString(roomDto);
        System.out.println(" room= "+str);
        return RoomDto.convertToDto(room);
    }
}
