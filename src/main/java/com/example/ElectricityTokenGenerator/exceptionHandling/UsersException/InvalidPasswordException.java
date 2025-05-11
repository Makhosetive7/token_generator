package com.example.ElectricityTokenGenerator.exceptionHandling.UsersException;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super("Invalid Password" + message);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super( "Invalid Password" + message, cause);
    }
    
}
