package com.example.ElectricityTokenGenerator.exceptionHandling.TokensException;

public class InvalidTransferException extends RuntimeException {
    public InvalidTransferException(String message) {
        super("Invalid Transfer: " + message);
    }

    public InvalidTransferException(String message, Throwable cause) {
        super("Invalid Transfer: " + message, cause);
    }
    
}
