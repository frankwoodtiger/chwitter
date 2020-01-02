package com.chi.chwitter.error.exception;

public class ChweetNotFoundException extends RuntimeException {
    public ChweetNotFoundException(Long id) {
        super("Chweet id not found : " + id);
    }
}
