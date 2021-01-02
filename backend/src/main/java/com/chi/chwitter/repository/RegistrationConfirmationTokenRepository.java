package com.chi.chwitter.repository;

import com.chi.chwitter.entity.RegistrationConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationConfirmationTokenRepository extends JpaRepository<RegistrationConfirmationToken, Long> {
    Optional<RegistrationConfirmationToken> findByToken(String token);
    Optional<RegistrationConfirmationToken> findByUser_Username(String username);
}