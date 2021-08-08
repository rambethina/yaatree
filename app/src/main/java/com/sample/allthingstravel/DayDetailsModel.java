package com.sample.allthingstravel;

import com.google.firebase.firestore.GeoPoint;

public class DayDetailsModel {

    long id;
    String title;
    String description;
    String time;
    String imageUrl;
    private String category;

    public GeoPoint getLatLng() {
        return latLng;
    }

    public void setLatLng(GeoPoint latLng) {
        this.latLng = latLng;
    }

    private GeoPoint latLng;


    public DayDetailsModel(long id, String title, String description, String time, String imageUrl, String category, GeoPoint latLng) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
        this.imageUrl = imageUrl;
        this.category = category;
        this.latLng = latLng;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
