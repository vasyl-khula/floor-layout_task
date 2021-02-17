package com.nerdysoft.task.floor_layout.exception;

import com.nerdysoft.task.floor_layout.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoomValidationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionDto roomValidationExceptionHandler(HttpServletRequest request, RoomValidationException exception){
        System.out.println("=====RoomValidationException handler===============");
        System.out.println(exception.getMessage());
        return ExceptionDto.convertToExceptionDto(exception);

    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto exceptionHandler(HttpServletRequest request, RoomValidationException exception){
        System.out.println(exception.getMessage());
        return ExceptionDto.convertToExceptionDto(exception);
    }

}
