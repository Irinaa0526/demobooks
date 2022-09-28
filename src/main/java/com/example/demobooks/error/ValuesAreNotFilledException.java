package com.example.demobooks.error;

public class ValuesAreNotFilledException extends RuntimeException{
    public ValuesAreNotFilledException() {
        super();
    }

    public ValuesAreNotFilledException(String message) {
        super(message);
    }

    public ValuesAreNotFilledException(String message, Throwable cause) {
        super(message, cause);
    }
}
