package com.empleabilidad.empleabilidad.Exceptions;

public class CustomException extends RuntimeException {
    private final int statusCode;
    private final String message;

    public CustomException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
