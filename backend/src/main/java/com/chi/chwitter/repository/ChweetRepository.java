package com.chi.chwitter.repository;

import com.chi.chwitter.entity.Chweet;
import com.chi.chwitter.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChweetRepository extends PagingAndSortingRepository<Chweet, Long> {
    public List<Chweet> findByUserOrderByCreatedDateDesc(User user);

    /*
        Even though Spring Data provides a PagingAndSortingRepository, we don't have to use it to get paging support (by
        returning a Page object). One can merely return a List<Chweet> here but we will pass a Page object to allow
        of flexible change of paging strategy.
     */
    public Page<Chweet> findByUser(User user, Pageable pageable);

    public long countByUser(User user);
    public long countByUser_Username(String username);

    /*
        findBy + (the foreign key member of Chweet class with first letter Upper) + underscore + the data member of
        User Class with first letter UpperCase + (String username);
     */
    public List<Chweet> findByUser_Username(String username);
}
