package com.souza.GradingSystem.exceptions;

public class InvalidGradeValueException extends RuntimeException {
    public InvalidGradeValueException(String message) {
        super(message);
    }
}
