package com.example.Claim_Module.ExceptionHandler.customException;

public class PolicyServiceException extends  RuntimeException{

    public PolicyServiceException (String message){
        super(message);
    }
}
