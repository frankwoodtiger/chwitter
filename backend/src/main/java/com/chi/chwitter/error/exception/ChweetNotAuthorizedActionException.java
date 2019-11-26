package com.chi.chwitter.error.exception;

public class ChweetNotAuthorizedActionException extends RuntimeException {
    public ChweetNotAuthorizedActionException(Long id) {
        super("Not authorize to edit the chweet " + id + ".");
    }
}
