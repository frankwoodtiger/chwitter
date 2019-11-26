package com.chi.chwitter.repository;

import com.chi.chwitter.entity.Chweet;
import com.chi.chwitter.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChweetRepository extends PagingAndSortingRepository<Chweet, Long> {
    public List<Chweet> findByUser(User user);

    /*
        findBy + (the foreign key member of Chweet class with first letter Upper) + underscore +the data member of
        User Class with first letter UpperCase +(String username);
     */
    public List<Chweet> findByUser_Username(String username);
}
