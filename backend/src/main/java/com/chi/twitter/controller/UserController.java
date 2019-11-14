package com.chi.twitter.controller;

import com.chi.twitter.form.UserRegistrationForm;
import com.chi.twitter.repository.UserRepository;
import com.chi.twitter.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/register")
    public String showRegisterUser(Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "userRegistration";
    }

    @PostMapping("/register")
    public String postRegisterUser(@Valid @ModelAttribute("userRegistrationForm") UserRegistrationForm userRegistrationForm,
                                   BindingResult bindingResult) {
        userServiceImpl.validate(userRegistrationForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "userRegistration";
        }
        userServiceImpl.saveUser(userRegistrationForm);
        return "redirect:/user/register";
    }
}
