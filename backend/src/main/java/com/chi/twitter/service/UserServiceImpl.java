package com.chi.twitter.service;

import com.chi.twitter.entity.User;
import com.chi.twitter.form.UserRegistrationForm;
import com.chi.twitter.repository.RoleRepository;
import com.chi.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.Collections;

@Service
public class UserServiceImpl {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void validate(UserRegistrationForm userForm, BindingResult bindingResult) {
        if (!StringUtils.isEmpty(userForm.getPassword()) && !userForm.getPassword().equals(userForm.getRetypePassword())) {
            bindingResult.rejectValue("retypePassword", "" ,
                    "Password is not the same as the retyped password.");
        }
    }

    private User userRegistrationFormToUser(UserRegistrationForm userForm) {
        User newUser = new User();
        newUser.setUsername(userForm.getUsername());
        newUser.setFirstName(userForm.getFirstName());
        newUser.setMiddleName(userForm.getMiddleName());
        newUser.setLastName(userForm.getLastName());
        newUser.setEmail(userForm.getEmail());
        newUser.setPassword(passwordEncoder.encode(userForm.getPassword()));
        newUser.setRoles(Collections.singleton(roleRepository.findByName("ROLE_USER")));
        return newUser;
    }

    public User saveUser(UserRegistrationForm userForm) {
        User newUser = userRegistrationFormToUser(userForm);
        return userRepository.save(newUser);
    }
}
