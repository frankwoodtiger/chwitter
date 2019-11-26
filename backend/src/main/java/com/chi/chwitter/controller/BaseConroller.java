package com.chi.chwitter.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseConroller {
    @Value("${spring.profiles.active:Unknown}")
    private String activeProfile;

    @ModelAttribute("activeProfile")
    public String getProfile() {
        return activeProfile;
    }
}
