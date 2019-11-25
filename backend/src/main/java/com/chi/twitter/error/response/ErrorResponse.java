package com.chi.twitter.error.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int httpStatus;
    private String errorMsg;

    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(int httpStatus, String errorMsg) {
        this.httpStatus = httpStatus;
        this.errorMsg = errorMsg;
    }
}
