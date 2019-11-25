package com.chi.twitter.controller;

import com.chi.twitter.entity.Tweet;
import com.chi.twitter.error.exception.TweetNotFoundException;
import com.chi.twitter.error.response.ErrorResponse;
import com.chi.twitter.repository.TweetRepository;
import com.chi.twitter.service.TweetServiceImpl;
import com.chi.twitter.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@PreAuthorize("!hasRole('ROLE_ANONYMOUS')")
public class TweetController extends BaseConroller {
    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    TweetServiceImpl tweetService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/tweets")
    public String tweets(Model model) {
        model.addAttribute("tweets", tweetService.findTweetsOfCurrentUser());
        return "tweets";
    }

    @PostMapping("/newTweet")
    public String newTweet(Model model, @RequestParam String message) {
        Tweet newTweet = new Tweet();
        newTweet.setMessage(message);
        newTweet.setUser(userService.getCurrentUser());
        Tweet savedTweet = tweetRepository.save(newTweet);
        model.addAttribute("tweet", savedTweet);
        return "tweet";
    }

    @DeleteMapping("/tweets/{id}")
    @ResponseBody // need this for void: https://stackoverflow.com/questions/12837907/what-to-return-if-spring-mvc-controller-method-doesnt-return-value
    public void deleteTweet(@PathVariable long id) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException(id));
        tweetService.validateTweetEditableByCurrentUser(tweet);
        tweetRepository.deleteById(id);
    }
}
