package com.example.bookmanagementsystem.exception;

public class JwtAuthorizationException extends RuntimeException {

    public JwtAuthorizationException() {
    }

    public JwtAuthorizationException(String message) {
        super(message);
    }

}