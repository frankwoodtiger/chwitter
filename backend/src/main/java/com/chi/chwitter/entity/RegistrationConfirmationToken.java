package com.chi.chwitter.entity;

import javax.persistence.*;
import java.util.UUID;

/*
Based mostly on https://stackabuse.com/spring-security-email-verification-registration/. There are other useful
reference like https://medium.com/@angela.amarapala/sending-email-confirmation-for-account-activation-with-spring-java-cc3f5bb1398e
 */
@Entity
public class RegistrationConfirmationToken extends AbstractEntity {
    private String confirmationToken;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private User user;

    public RegistrationConfirmationToken() { }

    public RegistrationConfirmationToken(User user) {
        this.user = user;
        this.confirmationToken = UUID.randomUUID().toString();
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
