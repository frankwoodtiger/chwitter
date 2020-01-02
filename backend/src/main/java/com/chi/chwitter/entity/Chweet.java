package com.chi.chwitter.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Chweet extends AbstractEntity {
    // @Column only to specify table column properties as it doesn't provide validations.
    // we can use @Column together with @Size to specify database column property with bean validation.
    @Column(length = 1024)
    @Size(max = 1024)
    private String message;

    // By default, @ManyToOne associations are fetched eagerly, and that’s bad for performance.
    @ManyToOne(fetch = FetchType.LAZY)
    /*
        It is not mandatory, JPA follows convention over configuration principle which means there are always some
        default values that you can override with annotations. In case of @JoinColumn, the default column name is
        generated like this: <field_name>_<id_column_name>
     */
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
