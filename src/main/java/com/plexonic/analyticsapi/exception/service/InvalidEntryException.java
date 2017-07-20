package com.plexonic.analyticsapi.exception.service;


public class InvalidEntryException extends RuntimeException {

    public InvalidEntryException(String message) {
        super(message);
    }

    public InvalidEntryException(String message, Exception e) {
        super(message, e);
    }
}
