package com.example.Claim_Module.ExceptionHandler.customException;

public class ClaimNotFoundException extends RuntimeException{

      public ClaimNotFoundException(String message){
          super(message);
      }

}
