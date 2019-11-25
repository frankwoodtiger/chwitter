package com.chi.twitter.service;

import com.chi.twitter.entity.Tweet;
import com.chi.twitter.entity.User;
import com.chi.twitter.error.exception.TweetNotAuthorizedActionException;
import com.chi.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImpl {
    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserServiceImpl userService;

    public void validateTweetEditableByCurrentUser(Tweet tweet) {
        if (!userService.getCurrentUser().equals(tweet.getUser())) {
            throw new TweetNotAuthorizedActionException(tweet.getId());
        }
    }

    public List<Tweet> findTweetsOfCurrentUser() {
        final User currentUser = userService.getCurrentUser();
        return tweetRepository.findByUser(currentUser);
    }
}
