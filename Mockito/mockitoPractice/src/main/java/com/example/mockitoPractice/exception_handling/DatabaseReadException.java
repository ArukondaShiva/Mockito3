package com.example.mockitoPractice.exception_handling;

public class DatabaseReadException extends RuntimeException{

    public DatabaseReadException(String message){
        super(message);
    }

}
