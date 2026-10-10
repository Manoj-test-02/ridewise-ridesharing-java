package com.airtribe.ridewise.model;

public class Rider {

    private Long riderId;
    private String riderName;
    private String riderMail;
    private double latitude;
    private double longitude;

    public Rider(Long riderId, String riderName, String riderMail, double latitude, double longitude) {
        this.riderId = riderId;
        this.riderName = riderName;
        this.riderMail = riderMail;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public Long getRiderId() {
        return riderId;
    }

    public void setRiderId(Long riderId) {
        this.riderId = riderId;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getRiderMail() {
        return riderMail;
    }

    public void setRiderMail(String riderMail) {
        this.riderMail = riderMail;
    }

    @Override
    public String toString() {
        return "Rider{" +
                "riderId=" + riderId +
                ", riderName='" + riderName + '\'' +
                ", riderMail='" + riderMail + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
