package com.chi.chwitter.service;

import com.chi.chwitter.entity.Chweet;
import com.chi.chwitter.entity.User;
import com.chi.chwitter.error.exception.ChweetNotAuthorizedActionException;
import com.chi.chwitter.repository.ChweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChweetServiceImpl {
    @Autowired
    private ChweetRepository chweetRepository;

    @Autowired
    private UserServiceImpl userService;

    public void validateChweetEditableByCurrentUser(Chweet chweet) {
        if (!userService.getCurrentUser().equals(chweet.getUser())) {
            throw new ChweetNotAuthorizedActionException(chweet.getId());
        }
    }

    public List<Chweet> findChweetsOfCurrentUser() {
        final User currentUser = userService.getCurrentUser();
        return chweetRepository.findByUser(currentUser);
    }
}
