package com.javastudy.ticketing.controller;

public class TicketForm {


    public int getUser_id() {
        return user_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    private int user_id;
    private int movie_id;


}
