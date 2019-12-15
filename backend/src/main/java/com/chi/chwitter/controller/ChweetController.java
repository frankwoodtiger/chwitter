package com.chi.chwitter.controller;

import com.chi.chwitter.entity.Chweet;
import com.chi.chwitter.error.exception.ChweetNotFoundException;
import com.chi.chwitter.service.ChweetServiceImpl;
import com.chi.chwitter.service.ImageServiceImpl;
import com.chi.chwitter.service.UserServiceImpl;
import com.chi.chwitter.repository.ChweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@PreAuthorize("!hasRole('ROLE_ANONYMOUS')")
public class ChweetController extends BaseConroller {
    @Autowired
    ChweetRepository chweetRepository;

    @Autowired
    ChweetServiceImpl chweetService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ImageServiceImpl imageService;

    @GetMapping("/chweets")
    public String chweets(Model model) {
        model.addAttribute("chweets", chweetService.findChweetsOfCurrentUser(true));
        model.addAttribute("followers", userService.getFollowersOfCurrentUser());
        model.addAttribute("followees", userService.getFolloweesOfCurrentUser());
        model.addAttribute("showEditChweet", true);
        return "chweets";
    }

    @GetMapping("/{username}/chweets")
    public String chweets(Model model, @PathVariable String username) {
        model.addAttribute("chweets", chweetService.findChweetsByUsername(username, true));
        model.addAttribute("followers", userService.getFollowersOfCurrentUser());
        model.addAttribute("followees", userService.getFolloweesOfCurrentUser());
        model.addAttribute("showEditChweet", false);
        return "chweets";
    }

    /*
        See: https://stackoverflow.com/questions/38645862/spring-mvc-4-ajax-upload-multipartfile-with-extra-parameters
     */
    @PostMapping("/newChweet")
    public String newChweet(Model model, @RequestParam String message, @RequestParam(required = false) MultipartFile image) {
        Chweet newChweet = new Chweet();
        newChweet.setMessage(message);
        newChweet.setUser(userService.getCurrentUser());
        Chweet savedChweet = chweetRepository.save(newChweet);
        if (image != null) {
            savedChweet = imageService.linkNewImage(image, savedChweet);
        }
        model.addAttribute("chweet", savedChweet);
        model.addAttribute("showEditChweet", true);
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
