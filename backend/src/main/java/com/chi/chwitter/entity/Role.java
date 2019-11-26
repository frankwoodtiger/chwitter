package com.chi.chwitter.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Role extends AbstractEntity {
    private String name;

    /*
        The mappedBy attribute of the users association in the Role entity marks that, in this bidirectional
        relationship, the User entity owns the association. This is needed since only one side can own a relationship,
        and changes are only propagated to the database from this particular side.
     */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
