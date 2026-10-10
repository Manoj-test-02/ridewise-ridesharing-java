package com.airtribe.ridewise.model;

public enum VehicleType {

    BIKE(10),
    AUTO(20),
    CAR(30);

    private final double fareCost;
    VehicleType(double fareCost) {
        this.fareCost = fareCost;
    }

    public double getFareCost() {
        return fareCost;
    }

}
