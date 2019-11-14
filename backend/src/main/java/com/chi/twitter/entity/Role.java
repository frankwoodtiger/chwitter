package com.chi.twitter.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Role extends AbstractEntity {
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
