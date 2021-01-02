package com.chi.chwitter.entity;

import javax.persistence.Entity;

@Entity
public class RegistrationConfirmationToken extends Token {
    public static final int EXPIRATION_DURATION = 90 * 60 * 24; // 3 months

    public RegistrationConfirmationToken() { }

    public RegistrationConfirmationToken(User user) {
        super(user);
    }

    @Override
    public int getExpirationDuration() {
        return EXPIRATION_DURATION;
    }
}
