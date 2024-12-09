package com.empleabilidad.empleabilidad.Exceptions;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(String message) {
        super(403, message);
    }
}
