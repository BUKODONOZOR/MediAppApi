package com.empleabilidad.empleabilidad.Exceptions;


public class InvalidAppointmentException extends RuntimeException {
    public InvalidAppointmentException(String message) {
        super(message);
    }
}