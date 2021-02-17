package com.nerdysoft.task.floor_layout.dto;

public class ExceptionDto {

    private String error;

    public ExceptionDto() {
    }

    public ExceptionDto(String message) {
        this.error = message;
    }

    public static ExceptionDto convertToExceptionDto(Exception e){
        return new ExceptionDto(e.getMessage());
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
