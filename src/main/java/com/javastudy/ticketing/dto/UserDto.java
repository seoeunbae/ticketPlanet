package com.javastudy.ticketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.Column;

public class UserDto {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @NotNull
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String email;
    @NotNull
    private String password;
}
