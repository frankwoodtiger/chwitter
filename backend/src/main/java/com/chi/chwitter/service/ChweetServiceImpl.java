package com.chi.chwitter.service;

import com.chi.chwitter.entity.Chweet;
import com.chi.chwitter.entity.User;
import com.chi.chwitter.error.exception.ChweetNotAuthorizedActionException;
import com.chi.chwitter.error.exception.ChweetNotFoundException;
import com.chi.chwitter.error.exception.UserNotFoundException;
import com.chi.chwitter.repository.ChweetRepository;
import com.chi.chwitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
public class ChweetServiceImpl {
    @Autowired
    private ChweetRepository chweetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ImageServiceImpl imageService;

    public void validateChweetEditableByCurrentUser(Chweet chweet) {
        if (!userService.getCurrentUser().equals(chweet.getUser())) {
            throw new ChweetNotAuthorizedActionException(chweet.getId());
        }
    }

    public List<Chweet> findChweetsOfUser(User user, boolean loadImage) {
        List<Chweet> chweets = chweetRepository.findByUserOrderByCreatedDateDesc(user);
        if (loadImage) {
            chweets.stream().filter(chweet -> chweet.getImage() != null && chweet.getImage().getData() != null)
                    .map(Chweet::getImage)
                    .forEach(image -> {
                        try {
                            image.setBase64Image(imageService.generateEncodedBase64String(image.getData()));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    });
        }
        return chweets;
    }

    public List<Chweet> findChweetsOfCurrentUser(boolean loadImage) {
        return findChweetsOfUser(userService.getCurrentUser(), loadImage);
    }

    public List<Chweet> findChweetsByUsername(String username, boolean loadImage) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return findChweetsOfUser(user.get(), loadImage);
        } else {
            throw new UserNotFoundException(username);
        }
    }

    @Transactional
    public void deleteChweetById(long id) {
        Chweet chweet = chweetRepository.findById(id)
                .orElseThrow(() -> new ChweetNotFoundException(id));
        validateChweetEditableByCurrentUser(chweet);
        chweetRepository.deleteById(id);
    }
}
