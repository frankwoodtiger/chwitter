package com.chi.chwitter.controller;

import com.chi.chwitter.repository.UserRepository;
import com.chi.chwitter.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController extends BaseConroller {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        if (userService.isAnonymous()) {
            return "home";
        }
        return "redirect:/chweets";
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

    // Handler routing to different pages when logged in
    @GetMapping("/login-success")
    public String loginSuccess(SecurityContextHolderAwareRequestWrapper request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else {
            return "redirect:/chweets";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("userList", userRepository.findAll());
        return "admin";
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnNoFavicon() {

    }
}
