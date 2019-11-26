package com.chi.chwitter.controller;

import com.chi.chwitter.service.UserServiceImpl;
import com.chi.chwitter.entity.User;
import com.chi.chwitter.form.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController extends BaseConroller {
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/register")
    public String showRegisterUser(Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "userRegistration";
    }

    @PostMapping("/register")
    public String postRegisterUser(RedirectAttributes model,
                                   @Valid @ModelAttribute("userRegistrationForm") UserRegistrationForm userRegistrationForm,
                                   BindingResult bindingResult) {
        userServiceImpl.validate(userRegistrationForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "userRegistration";
        }
        User newUser = userServiceImpl.saveUser(userRegistrationForm);
        model.addFlashAttribute("successMessage", "Successfully register " + newUser.getUsername());
        return "redirect:/user/register";
    }
}
