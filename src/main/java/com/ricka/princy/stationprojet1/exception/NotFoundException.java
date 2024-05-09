package com.ricka.princy.stationprojet1.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException{
    public NotFoundException() {
        super("Not found", HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
