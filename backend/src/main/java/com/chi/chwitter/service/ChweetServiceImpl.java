package com.chi.chwitter.service;

import com.chi.chwitter.entity.Chweet;
import com.chi.chwitter.entity.User;
import com.chi.chwitter.error.exception.ChweetNotAuthorizedActionException;
import com.chi.chwitter.error.exception.ChweetNotFoundException;
import com.chi.chwitter.error.exception.UserNotFoundException;
import com.chi.chwitter.repository.ChweetRepository;
import com.chi.chwitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
            loadImagesOnChweets(chweets.stream());
        }
        return chweets;
    }

    public Page<Chweet> findChweetsOfUser(User user, boolean loadImage, Pageable pageable) {
        Page<Chweet> chweetPage = chweetRepository.findByUser(user, pageable);
        if (loadImage) {
            loadImagesOnChweets(chweetPage.get());
        }
        return chweetPage;
    }

    private void loadImagesOnChweets(Stream<Chweet> chweetStream) {
        chweetStream.filter(chweet -> chweet.getImage() != null && chweet.getImage().getData() != null)
                .map(Chweet::getImage)
                .forEach(image -> {
                    try {
                        image.setBase64Image(imageService.generateEncodedBase64String(image.getData()));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                });
    }

    public List<Chweet> findChweetsOfCurrentUser(boolean loadImage) {
        return findChweetsOfUser(userService.getCurrentUser(), loadImage);
    }

    public Page<Chweet> findChweetsOfCurrentUser(boolean loadImage, Pageable pageable) {
        return findChweetsOfUser(userService.getCurrentUser(), loadImage, pageable);
    }

    public List<Chweet> findChweetsByUsername(String username, boolean loadImage) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return findChweetsOfUser(user.get(), loadImage);
        } else {
            throw new UserNotFoundException(username);
        }
    }

    public Page<Chweet> findChweetsByUsername(String username, boolean loadImage, Pageable pageable) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return findChweetsOfUser(user.get(), loadImage, pageable);
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

    public long getChweetCountByCurrentUser() {
        return getChweetCountByUser(userService.getCurrentUser());
    }

    public long getChweetCountByUsername(String username) {
        return chweetRepository.countByUser_Username(username);
    }

    public long getChweetCountByUser(User user) {
        return chweetRepository.countByUser(user);
    }
}
