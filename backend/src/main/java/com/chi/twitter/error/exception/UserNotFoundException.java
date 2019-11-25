package com.chi.twitter.error.exception;

public class UserNotFoundException  extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User id not found : " + id);
    }

    public UserNotFoundException(String username) {
        super("Username not found : " + username);
    }
}
