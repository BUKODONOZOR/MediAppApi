package com.empleabilidad.empleabilidad.Exceptions;

public class BadRequestException extends CustomException {
    public BadRequestException(String message) {
        super(400, message);
    }
}
