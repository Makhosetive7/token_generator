package com.example.ElectricityTokenGenerator.exceptionHandling.TokensException;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String message) {
        super("Token Expired: " + message);
    }

    public TokenExpiredException(String message, Throwable cause) {
        super("Token Expired: " + message, cause);
    }
    
}
