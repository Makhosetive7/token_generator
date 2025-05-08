package com.example.ElectricityTokenGenerator.exceptionHandling.UsersException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User Not Found" + message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super("User Not Found" + message, cause);
    }
    
}
