package com.chi.chwitter.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * REST/AJAX call response message wrapper.
 * Mostly based on https://www.toptal.com/java/spring-boot-rest-api-error-handling
 */
public class ApiResponse {
    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;
    private String debugMessage;
    private List<ApiSubResponse> apiSubResponses;

    public ApiResponse() {
        timestamp = LocalDateTime.now();
        apiSubResponses = new ArrayList<>();
    }

    public ApiResponse(HttpStatus status, String message) {
        this();
        this.status = status;
    }

    public ApiResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public List<ApiSubResponse> getApiSubResponses() {
        return apiSubResponses;
    }

    public void setApiSubResponses(List<ApiSubResponse> apiSubResponses) {
        this.apiSubResponses = apiSubResponses;
    }
}
