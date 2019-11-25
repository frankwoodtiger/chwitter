package com.chi.twitter.repository;

import com.chi.twitter.entity.Tweet;
import com.chi.twitter.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long> {
    public List<Tweet> findByUser(User user);

    /*
        findBy + (the foreign key member of Tweet class with first letter Upper) + underscore +the data member of
        User Class with first letter UpperCase +(String username);
     */
    public List<Tweet> findByUser_Username(String username);
}
