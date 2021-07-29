package com.techstack.user_service.exception;

public class UserNotFoundException extends RuntimeException{
    
    public UserNotFoundException (Long id) {
        super("Could not find user with given id: " + id);
    }
}
