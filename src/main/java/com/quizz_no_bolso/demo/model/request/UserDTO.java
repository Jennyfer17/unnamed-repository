package com.quizz_no_bolso.demo.model.request;

import com.quizz_no_bolso.demo.model.Status;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotNull(message = "First Name cannot be null")
    @Size(min = 2, message = "First Name must have at least 2 characters")
    private String firstName;
    @NotNull(message = "Last Name cannot be null")
    @Size(min = 2, message = "Last Name must have at least 2 characters")
    private String lastName;
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;
    private Status status;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
