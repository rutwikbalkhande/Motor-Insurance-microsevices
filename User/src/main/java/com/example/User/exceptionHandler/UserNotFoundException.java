package com.example.User.exceptionHandler;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long userId){
        super("useer Not found witrh id :" + userId);
    }
}
