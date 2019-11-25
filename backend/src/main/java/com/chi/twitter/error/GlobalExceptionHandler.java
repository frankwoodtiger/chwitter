package com.chi.twitter.error;

import com.chi.twitter.error.exception.TweetNotAuthorizedActionException;
import com.chi.twitter.error.exception.TweetNotFoundException;
import com.chi.twitter.error.exception.UserNotFoundException;
import com.chi.twitter.error.response.ErrorResponse;
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
        TweetNotFoundException.class,
        UserNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ TweetNotAuthorizedActionException.class })
    public ResponseEntity<ErrorResponse> handleNotAuthorized(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

}
