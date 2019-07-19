package com.example.books;

import android.media.Image;

public class Book {
    private String title;
    private String picture;
    private String message;

    public Book() {
    }

    public Book(String title, String Picture, String message) {
        this.title = title;
        this.message = message;
        picture = Picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
