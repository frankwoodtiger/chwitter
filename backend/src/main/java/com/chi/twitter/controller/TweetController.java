package com.chi.twitter.controller;

import com.chi.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("!hasRole('ROLE_ANONYMOUS')")
public class TweetController extends BaseConroller {
    @Autowired
    TweetRepository tweetRepository;

    @GetMapping("/tweets")
    public String tweets(Model model) {
        model.addAttribute("tweets", tweetRepository.findAll());
        return "tweets";
    }
}
