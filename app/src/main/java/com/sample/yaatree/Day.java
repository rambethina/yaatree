package com.sample.yaatree;

public class Day {

    private String dayStr;
    private String imageURL;
    private int id;


    public Day(String dayStr, String imageURL, int id) {
        this.dayStr = dayStr;
        this.imageURL = imageURL;
        this.id = id;
    }

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
