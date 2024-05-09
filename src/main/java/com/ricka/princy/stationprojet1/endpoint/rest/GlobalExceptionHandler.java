package com.ricka.princy.stationprojet1.endpoint.rest;

import com.ricka.princy.stationprojet1.exception.ApiErrorResponse;
import com.ricka.princy.stationprojet1.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(ApiException error) {
        return new ResponseEntity<>(
                new ApiErrorResponse(error.getMessage(), error.getStatus()),
                error.getStatus()
        );
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(RuntimeException error) {
        error.printStackTrace();
        return new ResponseEntity<>(
                new ApiErrorResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
