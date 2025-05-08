package com.example.ElectricityTokenGenerator.exceptionHandling.UsersException;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException(String message) {
        super("Account Already Exists: " + message);
    }

    public AccountAlreadyExistsException(String message, Throwable cause) {
        super("Account Already Exists: " + message, cause);
    }
    
}
