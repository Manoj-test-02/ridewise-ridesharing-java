package com.airtribe.ridewise.model;

public class Driver {

    private Long driverId;
    private String driverName;
    private String driverMail;
    private double latitude;
    private double longitude;
    private boolean available;
    private VehicleType vehicleType;
    private int rideCount;

    public Driver(Long driverId, String driverName, String driverMail, double latitude, double longitude, boolean available,VehicleType vehicleType) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverMail = driverMail;
        this.latitude = latitude;
        this.longitude = longitude;
        this.available = available;
        this.vehicleType = vehicleType;
        this.rideCount = 0;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDriverMail() {
        return driverMail;
    }

    public void setDriverMail(String driverMail) {
        this.driverMail = driverMail;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getRideCount() {
        return rideCount;
    }

    public void setRideCount(int rideCount) {
        this.rideCount = rideCount;
    }

    public void incrementRideCount(){
        this.rideCount++;
    }
    public void decrementRideCount(){
        this.rideCount--;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", driverMail='" + driverMail + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", available=" + available +
                ", vehicleType=" + vehicleType.toString() +
                '}';
    }
}
