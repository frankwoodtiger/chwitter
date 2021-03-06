package com.chi.chwitter.error.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ResponseData {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int httpStatus;
    private String errorMsg;

    public ResponseData() {
        this.timestamp = LocalDateTime.now();
    }

    public ResponseData(int httpStatus, String errorMsg) {
        this.httpStatus = httpStatus;
        this.errorMsg = errorMsg;
    }
}
