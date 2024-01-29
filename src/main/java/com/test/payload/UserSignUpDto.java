package com.test.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserSignUpDto {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, message = "Name should have at least 3 character")
    private String name;
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 3, message = "Username should have at least 3 character")
    private String userName;
    @NotEmpty(message = "Email Should not be empty")
    @Email(message = "Email should be a valid email")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 3, message = "Password should have at least 3 character")
    private String password;

    private String status = "InActive";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
