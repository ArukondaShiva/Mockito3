package com.example.mockitoPractice.exception_handling;

public class DatabaseWriteException extends RuntimeException {
    public DatabaseWriteException(String message){
        super(message);
    }
}
