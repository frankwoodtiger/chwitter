package com.chi.twitter.controllers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login(RedirectAttributes model, HttpServletRequest request, @RequestParam(required = false) String error,
                        @RequestParam(required = false) String logout) {
        String errorMessage = null;
        if (error != null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                AuthenticationException ex = (AuthenticationException) session
                        .getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
                if (ex != null) {
                    errorMessage = ex.getMessage();
                }
            }
            model.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/";
        }
        if (logout != null) {
            model.addFlashAttribute("errorMessage", "You have been logged out.");
            return "redirect:/";
        }
        return "login";
    }
}
