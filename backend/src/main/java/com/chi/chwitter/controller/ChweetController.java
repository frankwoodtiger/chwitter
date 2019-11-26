package com.chi.chwitter.controller;

import com.chi.chwitter.entity.Chweet;
import com.chi.chwitter.error.exception.ChweetNotFoundException;
import com.chi.chwitter.service.ChweetServiceImpl;
import com.chi.chwitter.service.UserServiceImpl;
import com.chi.chwitter.repository.ChweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("!hasRole('ROLE_ANONYMOUS')")
public class ChweetController extends BaseConroller {
    @Autowired
    ChweetRepository chweetRepository;

    @Autowired
    ChweetServiceImpl chweetService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/chweets")
    public String chweets(Model model) {
        model.addAttribute("chweets", chweetService.findChweetsOfCurrentUser());
        return "chweets";
    }

    @PostMapping("/newChweet")
    public String newChweet(Model model, @RequestParam String message) {
        Chweet newChweet = new Chweet();
        newChweet.setMessage(message);
        newChweet.setUser(userService.getCurrentUser());
        Chweet savedChweet = chweetRepository.save(newChweet);
        model.addAttribute("chweet", savedChweet);
        return "chweet";
    }

    @DeleteMapping("/chweets/{id}")
    @ResponseBody // need this for void: https://stackoverflow.com/questions/12837907/what-to-return-if-spring-mvc-controller-method-doesnt-return-value
    public void deleteChweet(@PathVariable long id) {
        Chweet chweet = chweetRepository.findById(id)
                .orElseThrow(() -> new ChweetNotFoundException(id));
        chweetService.validateChweetEditableByCurrentUser(chweet);
        chweetRepository.deleteById(id);
    }
}
