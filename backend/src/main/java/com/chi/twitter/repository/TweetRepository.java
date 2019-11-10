package com.chi.twitter.repository;

import com.chi.twitter.entity.Tweet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long> {

}
