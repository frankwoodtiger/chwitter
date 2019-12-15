package com.chi.chwitter.service;

import com.chi.chwitter.entity.User;
import com.chi.chwitter.error.exception.UserNotFoundException;
import com.chi.chwitter.form.UserRegistrationForm;
import com.chi.chwitter.mapper.UserDTO;
import com.chi.chwitter.mapper.UserMapper;
import com.chi.chwitter.projection.PotentialFollower;
import com.chi.chwitter.repository.RoleRepository;
import com.chi.chwitter.repository.ChweetRepository;
import com.chi.chwitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {
    private final static String ANONYMOUS_USER_ID = "anonymous";
    private final static String ROLE_USER_KEY = "ROLE_USER";

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ChweetRepository chweetRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void validate(UserRegistrationForm userForm, BindingResult bindingResult) {
        if (!StringUtils.isEmpty(userForm.getPassword()) && !userForm.getPassword().equals(userForm.getRetypePassword())) {
            bindingResult.rejectValue("retypePassword", "" ,
                    "Password is not the same as the retyped password.");
        }
        if (!userRepository.findByUsername(userForm.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", "" ,
                    "Username already exists. Please use another username.");
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
        newUser.getRoles().add(roleRepository.findByName("ROLE_USER"));
        return newUser;
    }

    public User saveUser(UserRegistrationForm userForm) {
        User newUser = userRegistrationFormToUser(userForm);
        return userRepository.save(newUser);
    }

    public boolean isAnonymous() {
        /*
            request.isUserInRole("ROLE_ANONYMOUS") is not a valid way to check whether is anonymous or not as
            the authentication is null for anonymous user. Should check whether the Authentication from SecurityContext
            is instance of AnonymousAuthenticationToken or not
            See: https://stackoverflow.com/questions/26101738/why-is-the-anonymoususer-authenticated-in-spring-security/26117007
         */
        return SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
    }

    public User getCurrentUser() {
        if (!isAnonymous()) {
            String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByUsername(currentUserName)
                    .orElseThrow(() -> new UserNotFoundException(currentUserName));
        }
        return getAnonymousUser();
    }

    @Transactional
    public List<UserDTO> getFollowersOfCurrentUser() {
        return userMapper.usersToUserDTOs(getCurrentUser().getFollowers().stream()
                .collect(Collectors.toList()));
    }

    @Transactional
    public List<UserDTO> getFolloweesOfCurrentUser() {
        return userMapper.usersToUserDTOs(getCurrentUser().getFollowees().stream()
                .collect(Collectors.toList()));
    }

    public User getAnonymousUser() {
        User anonymousUser = new User();
        anonymousUser.setUsername(ANONYMOUS_USER_ID);
        return anonymousUser;
    }

    public Set<PotentialFollower> findPotentialFollowersByKeyword(String keyword) {
        return userRepository.findPotentialFollowersByKeyword(
                getCurrentUser(), roleRepository.findByName(ROLE_USER_KEY), keyword);
    }

    @Transactional
    public boolean followUserById(Long id) {
        Optional<User> toFollow = userRepository.findById(id);
        if (toFollow.isPresent() && !getCurrentUser().getFollowers().contains(toFollow.get())) {
            User currentUser = getCurrentUser();
            User follower = toFollow.get();
            currentUser.addFollower(follower);
            userRepository.save(follower);
            userRepository.save(currentUser);
            return true;
        }
        return false;
    }
}
