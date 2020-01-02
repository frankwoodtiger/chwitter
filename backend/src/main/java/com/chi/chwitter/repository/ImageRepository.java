package com.chi.chwitter.repository;

import com.chi.chwitter.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/*
    for SINGLE_TABLE strategy, to query subclass, Spring will automatically do it if you use Image as generic type
    below. See: https://stackoverflow.com/questions/56189962/query-the-database-using-discriminator-values
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

}
