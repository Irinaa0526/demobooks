package com.example.demobooks.error;

public class AuthorsIsEmptyException extends Exception{
    public AuthorsIsEmptyException(String message) {
        super(message);
    }
}
