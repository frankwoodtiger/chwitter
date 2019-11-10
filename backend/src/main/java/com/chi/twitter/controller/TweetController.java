package com.chi.twitter.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TweetController {
    @PreAuthorize("!hasRole('ROLE_ANONYMOUS')")
    @GetMapping("/tweets")
    public String tweets() {
        return "tweets";
    }
}
