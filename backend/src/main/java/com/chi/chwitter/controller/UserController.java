package com.chi.chwitter.controller;

import com.chi.chwitter.service.UserServiceImpl;
import com.chi.chwitter.entity.User;
import com.chi.chwitter.form.UserRegistrationForm;
import com.chi.chwitter.util.TokenUtils;
import com.chi.chwitter.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
        try {
            User newUser = userService.saveUser(userRegistrationForm);
            model.addFlashAttribute("successMessage",
                    "Successfully register " + newUser.getUsername() + ". Please check your email to activate your account.");
        } catch (MailException e) {
            model.addFlashAttribute("errorMessage",
                    "Server is unable to send email. Please try to register again later.");
        }
        return "redirect:/user/register";
    }

    @GetMapping("/confirm")
    public String confirm(Model model, @RequestParam("token") String confirmationToken) {
        model.addAttribute("title", "Account Confirmation");
        userService.findRegistrationTokenByToken(confirmationToken).ifPresentOrElse(token -> {
            User user = token.getUser();
            if (TokenUtils.isTokenExpired(token)) {
                model.addAttribute("errorMessage",
                        "Your confirmation token expired. Please enter user id for your new confirmation request.");
            } else {
                user.setActivated(true);
                userService.save(user);
                model.addAttribute("successMessage",
                        "Welcome " + user.getUsername() + "! Your account is successfully activated.");
            }
        }, () -> model.addAttribute("errorMessage", "Invalid Token."));
        return "emptyPageWithMessage";
    }

    @RequestMapping(value = "/refreshRegistrationToken", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<ApiResponse> refreshRegistrationToken(@RequestParam("username") String username) {
        ApiResponse response = new ApiResponse();
        try {
            userService.findRegistrationTokenByUsername(username).ifPresentOrElse(token -> {
                userService.refreshToken(token);
                response.setStatus(HttpStatus.OK);
                response.setMessage("Registration token has been refreshed successfully!");
            }, () -> {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                response.setMessage("Registration token not found. Please register again.");
            });
        } catch (MailException e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage("Server is unable to send email. Please try to register again later.");
        }
        // Return response entity as we also want to send the proper HTTP status back to browser
        return new ResponseEntity<>(response, response.getStatus());
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
