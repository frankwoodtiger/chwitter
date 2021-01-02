package com.chi.chwitter.web;

/**
 * Lightweight response (without stacktrace) for response with multiple message scenario.
 */
public class ApiSubResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
