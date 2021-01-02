package com.chi.chwitter.entity;

import com.chi.chwitter.util.TokenUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/*
    Based mostly on https://stackabuse.com/spring-security-email-verification-registration/. There are other useful
    reference like:
        https://www.baeldung.com/registration-verify-user-by-email
        https://medium.com/@angela.amarapala/sending-email-confirmation-for-account-activation-with-spring-java-cc3f5bb1398e

    Using @MappedSuperclass as we don't really need to instantiate this class and this class is
    just a container of shared attributes.
*/
@MappedSuperclass
public abstract class Token extends AbstractEntity {
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private User user;

    /* If one wants resolution to be just up to the day, use TemporalType.DATE */
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    public Token(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
        setExpiryDate();
    }

    public Token() { }

    /* Declared as abstract so that subclass can have different duration */
    public abstract int getExpirationDuration();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate() {
        this.expiryDate = TokenUtils.calculateExpiryDate(getExpirationDuration());
    }
}
