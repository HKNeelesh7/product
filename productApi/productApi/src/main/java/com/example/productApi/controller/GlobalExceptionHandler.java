package com.example.productApi.controller;

import com.example.productApi.exception.InvalidInputDataException;
import com.example.productApi.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFound(ProductNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Exception", ex.getMessage());
        error.put("Status", HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputDataException.class)
    public ResponseEntity<Map<String, String>> handleInvalidInputData(InvalidInputDataException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Exception", ex.getMessage());
        error.put("Status", HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
