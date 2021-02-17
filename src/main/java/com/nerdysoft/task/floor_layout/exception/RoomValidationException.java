package com.nerdysoft.task.floor_layout.exception;

public class RoomValidationException extends RuntimeException{

    public RoomValidationException(){}

    public RoomValidationException(String message){
        super(message);
    }

}
