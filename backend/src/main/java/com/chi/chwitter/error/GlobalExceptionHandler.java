package com.chi.chwitter.error;

import com.chi.chwitter.error.response.ResponseData;
import com.chi.chwitter.error.exception.ChweetNotAuthorizedActionException;
import com.chi.chwitter.error.exception.ChweetNotFoundException;
import com.chi.chwitter.error.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// see: https://www.mkyong.com/spring-boot/spring-rest-error-handling-example/

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
        ChweetNotFoundException.class,
        UserNotFoundException.class
    })
    public ResponseEntity<ResponseData> handleNotFound(Exception ex, WebRequest request) {
        ResponseData responseData = new ResponseData(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());
        return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ ChweetNotAuthorizedActionException.class })
    public ResponseEntity<ResponseData> handleNotAuthorized(Exception ex, WebRequest request) {
        ResponseData responseData = new ResponseData(HttpStatus.UNAUTHORIZED.value(), ex.getLocalizedMessage());
        return new ResponseEntity<>(responseData, HttpStatus.UNAUTHORIZED);
    }

}
