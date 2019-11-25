package com.chi.twitter.error.exception;

public class TweetNotFoundException extends RuntimeException {
    public TweetNotFoundException(Long id) {
        super("Tweet id not found : " + id);
    }
}
