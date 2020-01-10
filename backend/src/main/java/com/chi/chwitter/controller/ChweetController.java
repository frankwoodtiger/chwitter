package com.chi.chwitter.controller;

import com.chi.chwitter.entity.Chweet;
import com.chi.chwitter.paging.OffsetLimitPageRequest;
import com.chi.chwitter.service.ChweetServiceImpl;
import com.chi.chwitter.service.ImageServiceImpl;
import com.chi.chwitter.service.UserServiceImpl;
import com.chi.chwitter.repository.ChweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@Controller
@PreAuthorize("!hasRole('ROLE_ANONYMOUS')")
public class ChweetController extends BaseConroller {
    private static final int DEFAULT_PAGE_SIZE = 5;
    private static final String CURRENT_USER_PAGING_URL = "/chweets/page";
    private static final String USER_PAGING_URL_EXP = "/%s/chweets/page";

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
        Pageable defaultPageable = PageRequest.of(0, DEFAULT_PAGE_SIZE, Sort.by(Order.desc("createdDate")));
        model.addAttribute("chweets",
                chweetService.findChweetsOfCurrentUser(true, defaultPageable).getContent());
        model.addAttribute("chweetTotalCount", chweetService.getChweetCountByCurrentUser());
        model.addAttribute("followers", userService.getFollowersOfCurrentUser());
        model.addAttribute("followees", userService.getFolloweesOfCurrentUser());
        model.addAttribute("showEditChweet", true);
        model.addAttribute("pagingURL", CURRENT_USER_PAGING_URL);
        return "chweetsPage";
    }

    @GetMapping("/chweets/page")
    public String chweets(Model model, @RequestParam int chweetCountOnPage) {
        Pageable pageable = new OffsetLimitPageRequest(
                chweetCountOnPage, DEFAULT_PAGE_SIZE, Sort.by(Order.desc("createdDate")));
        model.addAttribute("chweets",
                chweetService.findChweetsOfCurrentUser(true, pageable).getContent());
        model.addAttribute("showEditChweet", true);
        return "chweets";
    }

    @GetMapping("/{username}/chweets")
    public String chweets(Model model, @PathVariable String username) {
        Pageable defaultPageable = PageRequest.of(0, DEFAULT_PAGE_SIZE, Sort.by(Order.desc("createdDate")));
        model.addAttribute("chweets",
                chweetService.findChweetsByUsername(username, true, defaultPageable).getContent());
        model.addAttribute("chweetTotalCount", chweetService.getChweetCountByUsername(username));
        model.addAttribute("followers", userService.getFollowersOfCurrentUser());
        model.addAttribute("followees", userService.getFolloweesOfCurrentUser());
        model.addAttribute("showEditChweet", false);
        model.addAttribute("pagingURL", String.format(USER_PAGING_URL_EXP, username));
        return "chweetsPage";
    }

    @GetMapping("/{username}/chweets/page")
    public String chweets(Model model, @PathVariable String username, @RequestParam int chweetCountOnPage) {
        Pageable pageable = new OffsetLimitPageRequest(
                chweetCountOnPage, DEFAULT_PAGE_SIZE, Sort.by(Order.desc("createdDate")));
        model.addAttribute("chweets",
                chweetService.findChweetsByUsername(username, true, pageable).getContent());
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
        model.addAttribute("chweets", Collections.singletonList(savedChweet));
        model.addAttribute("showEditChweet", true);
        return "chweets";
    }

    @DeleteMapping("/chweets/{id}")
    @ResponseBody // need this for void: https://stackoverflow.com/questions/12837907/what-to-return-if-spring-mvc-controller-method-doesnt-return-value
    public void deleteChweet(@PathVariable long id) {
        chweetService.deleteChweetById(id);
    }
}
