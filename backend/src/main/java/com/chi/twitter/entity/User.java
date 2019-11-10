package com.chi.twitter.entity;

import javax.persistence.Entity;
import javax.persistence.Column;

@Entity
public class User extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;
}
