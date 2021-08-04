package com.javastudy.ticketing.controller;

import java.util.Date;

public class MovieForm {
    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String title;
    private int price;
    private String date;
}
