package org.example.exception;

public class ValidationException extends RuntimeException {

    private String exceptionMessage;

    public ValidationException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}
