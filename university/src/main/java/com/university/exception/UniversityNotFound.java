package com.university.exception;

public class UniversityNotFound extends RuntimeException {
    public  UniversityNotFound(String message){
        super(message);
    }
}
