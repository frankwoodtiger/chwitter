package com.chi.chwitter.controller;

import com.chi.chwitter.service.UserServiceImpl;
import com.chi.chwitter.entity.User;
import com.chi.chwitter.form.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController extends BaseConroller {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/register")
    public String showRegisterUser(Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "userRegistration";
    }

    @PostMapping("/register")
    public String postRegisterUser(RedirectAttributes model,
                                   @Valid @ModelAttribute("userRegistrationForm") UserRegistrationForm userRegistrationForm,
                                   BindingResult bindingResult) {
        userService.validate(userRegistrationForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "userRegistration";
        }
        User newUser = userService.saveUser(userRegistrationForm);
        model.addFlashAttribute("successMessage", "Successfully register " + newUser.getUsername());
        return "redirect:/user/register";
    }

    @PreAuthorize("!hasRole('ROLE_ANONYMOUS')")
    @GetMapping("/followees")
    public Set<User> followees() {
        return userService.getCurrentUser().getFollowees();
    }

    @PreAuthorize("!hasRole('ROLE_ANONYMOUS')")
    @GetMapping("/followers")
    public Set<User> follower() {
        return userService.getCurrentUser().getFollowers();
    }

    @PreAuthorize("!hasRole('ROLE_ANONYMOUS')")
    @GetMapping("/users")
    public String users(@RequestParam String keyword, Model model) {
        model.addAttribute("potentialUsers", userService.findPotentialFollowersByKeyword(keyword));
        return "potentialFollowers";
    }

    @PreAuthorize("!hasRole('ROLE_ANONYMOUS')")
    @GetMapping("/{userId}/follow")
    public String follow(@PathVariable Long userId, Model model) {
        userService.followUserById(userId);
        model.addAttribute("followers", userService.getFollowersOfCurrentUser());
        return "followers";
    }
}
