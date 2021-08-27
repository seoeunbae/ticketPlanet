package com.javastudy.ticketing.dto;

public class TokenDto {

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenDto(String token) {
        this.token = token;
    }

    private String token;

}
