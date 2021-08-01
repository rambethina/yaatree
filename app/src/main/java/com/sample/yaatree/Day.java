package com.sample.yaatree;

public class Day {

    private String dayStr;
    private String imageURL;
    private long id;


    public Day(String dayStr, String imageURL, long id) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
