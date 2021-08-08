package com.sample.allthingstravel;

public class Day {

    private String documentId;
    private String summary;
    private String imageURL;
    private String tripDate;
    private long id;


    public Day(String documentId, String dayStr, String imageURL, String tripDate, long id) {
        this.documentId = documentId;
        this.summary = dayStr;
        this.imageURL = imageURL;
        this.tripDate = tripDate;
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
