package com.example.Policy.exceptionHandling;

public class PolicyNotFoundException extends RuntimeException{

    public PolicyNotFoundException(String message){
        super(message);
    }
}
