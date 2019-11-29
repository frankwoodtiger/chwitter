package com.chi.chwitter.service;

import com.chi.chwitter.entity.Chweet;
import com.chi.chwitter.entity.User;
import com.chi.chwitter.error.exception.ChweetNotAuthorizedActionException;
import com.chi.chwitter.repository.ChweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class ChweetServiceImpl {
    @Autowired
    private ChweetRepository chweetRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ImageServiceImpl imageService;

    public void validateChweetEditableByCurrentUser(Chweet chweet) {
        if (!userService.getCurrentUser().equals(chweet.getUser())) {
            throw new ChweetNotAuthorizedActionException(chweet.getId());
        }
    }

    public List<Chweet> findChweetsOfCurrentUser(boolean loadImage) {
        final User currentUser = userService.getCurrentUser();
        List<Chweet> chweets = chweetRepository.findByUserOrderByCreatedDateDesc(currentUser);
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
}
