package com.chi.twitter.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationForm {
    @NotNull(message = "Please enter an username.")
    @Size(min = 1, max = 255, message = "Please enter between {min} and {max} characters.")
    private String username;

    @Size(min = 1, max = 255, message = "Please enter between {min} and {max} characters.")
    private String firstName;

    @Size(max = 255, message = "Please enter under {max} characters.")
    private String middleName;

    @Size(min = 1, max = 255, message = "Please enter between {min} and {max} characters.")
    private String lastName;

    @NotNull(message = "Please enter an email.")
    @Email(message = "Please enter a valid email.")
    private String email;

    @NotNull(message = "Please enter an password.")
    @Size(min = 4, max = 255, message = "Please enter between {min} and {max} characters.")
    private String password;

    @NotNull(message = "Please retype your password.")
    @Size(min = 4, max = 255, message = "Please enter between {min} and {max} characters.")
    private String retypePassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }
}
