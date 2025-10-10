package com.example.User.exceptionHandler;

public class InvalidInputException extends RuntimeException{

    public InvalidInputException (String message){
        super(message);
    }
}
