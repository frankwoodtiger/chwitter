package com.chi.chwitter.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    @Column(columnDefinition = "boolean default false")
    private boolean isActivated;

    /*
        It’s not a good idea to use the Collection/List for @ManyToMany JPA associations. Better use Set for
        unique contraint generation on both side of the entity such that each pair in the relationship table
        (users_roles) is unique.

        See: https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/

        More many-to-many links:
        Basic many-to-many and composite key table: https://www.baeldung.com/jpa-many-to-many
     */
    @ManyToMany(fetch = FetchType.EAGER) // FetchType.EAGER always fetch roles as we assume a user won't have a large number of role
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    // seems like good practice to initialize the set:
    // https://docs.jboss.org/hibernate/core/3.3/reference/en/html/collections.html#collections-persistent
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "followees_followers",
            joinColumns = @JoinColumn(name = "followee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"))
    Set<User> followees = new HashSet<>(); // People who follow you

    @ManyToMany(mappedBy = "followees")
    Set<User> followers = new HashSet<>(); // People you follow

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setFollowees(Set<User> followees) {
        this.followees = followees;
    }

    public Set<User> getFollowees() {
        return followees;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    // reference entity model: https://gist.github.com/ffbit/3343910
    public void addFollower(User follower) {
        followers.add(follower);
        follower.followees.add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
