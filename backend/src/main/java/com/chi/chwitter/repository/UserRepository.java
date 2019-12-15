package com.chi.chwitter.repository;

import com.chi.chwitter.entity.User;
import com.chi.chwitter.projection.PotentialFollower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // More on using JPQL: https://thoughts-on-java.org/jpql/
    @Query("select u from User u where " +
            ":currentUser not member of u.followees and " + // exclude users already followed
            "u <> :currentUser and " + // exclude himself/herself
            "(lower(u.username) like concat('%', lower(:keyword), '%') or " +
            "lower(u.firstName) like concat('%', lower(:keyword), '%') or " +
            "lower(u.middleName) like concat('%', lower(:keyword), '%') or " +
            "lower(u.lastName) like concat('%', lower(:keyword), '%'))")
    Set<PotentialFollower> findPotentialFollowersByKeyword(@Param("currentUser") User currentUser, @Param("keyword") String keyword);
}
