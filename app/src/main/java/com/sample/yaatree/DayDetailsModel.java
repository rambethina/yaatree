package com.sample.yaatree;

public class DayDetailsModel {

    int id;
    String title;
    String description;
    String time;
    String imageUrl;


    public DayDetailsModel(int id, String title, String description, String time, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
