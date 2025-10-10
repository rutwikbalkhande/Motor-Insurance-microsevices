package com.example.User.exceptionHandler;

public class PolicynotFoundException extends RuntimeException{

    public  PolicynotFoundException(String message){

        super(message);
    }
}
