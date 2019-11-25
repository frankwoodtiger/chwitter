package com.chi.twitter.error.exception;

public class TweetNotAuthorizedActionException extends RuntimeException {
    public TweetNotAuthorizedActionException(Long id) {
        super("Not authorize to edit the tweet " + id + ".");
    }
}
