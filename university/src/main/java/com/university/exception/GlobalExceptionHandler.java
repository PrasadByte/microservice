package com.university.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UniversityNotFound.class)
    public ResponseEntity<String>  handleUniversityNotFound(UniversityNotFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> hangleEntityNotFound2(EntityNotFoundException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("status", HttpStatus.NOT_FOUND);
        map.put("Time and Date ",  System.currentTimeMillis());
        return ResponseEntity.ok(map);
    }

}
