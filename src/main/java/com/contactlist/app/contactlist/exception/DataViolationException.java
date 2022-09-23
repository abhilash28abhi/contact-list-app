package com.contactlist.app.contactlist.exception;

/**
 * Custom exception for data violation errors
 */
public class DataViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataViolationException() {}

    public DataViolationException(String message) {
        super(message);
    }
}
