package com.empleabilidad.empleabilidad.Exceptions;

public class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException(String message) {
        super(404, message);
    }
}

