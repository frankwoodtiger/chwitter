package com.chi.twitter.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterForm {
    @NotNull
    private String userId;

    @Size(min = 1, max = 255, message = "Please enter between {min} and {max} characters.")
    private String firstName;

    @Size(min = 1, max = 255, message = "Please enter between {min} and {max} characters.")
    private String middleName;

    @Size(min = 1, max = 255, message = "Please enter between {min} and {max} characters.")
    private String LastName;

    @Email
    private String email;

    @NotNull
    @Size(min = 4, max = 255, message = "Please enter between {min} and {max} characters.")
    private String password;

    @NotNull
    @Size(min = 4, max = 255, message = "Please enter between {min} and {max} characters.")
    private String retypePassword;
}
