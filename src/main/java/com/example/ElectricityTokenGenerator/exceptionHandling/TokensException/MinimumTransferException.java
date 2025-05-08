package com.example.ElectricityTokenGenerator.exceptionHandling.TokensException;

public class MinimumTransferException extends RuntimeException {
    public MinimumTransferException(String message) {
        super("Minimum Transfer Exception: " + message);
    }

    public MinimumTransferException(String message, Throwable cause) {
        super("Minimum Transfer Exception: " + message, cause);
    }
    
}
