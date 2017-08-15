package com.example.mac.booklistingapp;

/**
 * Created by mac on 10/08/17.
 */

public class BookItems {

    private  String title;
    private String description;
    private String date;
    private String link;

    public BookItems(String title, String description, String date, String link) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String author) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }
}
