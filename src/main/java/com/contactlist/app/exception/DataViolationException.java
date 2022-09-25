package com.contactlist.app.exception;

/**
 * Custom exception class to indicate data violation errors for user input values.
 */
public class DataViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataViolationException() {}

    public DataViolationException(String message) {
        super(message);
    }
}
