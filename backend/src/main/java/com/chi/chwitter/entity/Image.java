package com.chi.chwitter.entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("IMAGE")
public class Image extends File {
    @OneToOne(mappedBy = "image")
    private Chweet chweet;

    @Transient
    private String base64Image;

    public Chweet getChweet() {
        return chweet;
    }

    public void setChweet(Chweet chweet) {
        this.chweet = chweet;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
