package com.example.ElectricityTokenGenerator.exceptionHandling.TokensException;

public class InvalidKilowattValueException extends RuntimeException {
    public InvalidKilowattValueException(String message) {
        super("Invalid Kilowatt Value: " + message);
    }

    public InvalidKilowattValueException(String message, Throwable cause) {
        super("Invalid Kilowatt Value: " + message, cause);
    }
    
}
