package com.airtribe.ridewise.model;

import java.time.LocalDate;
import java.util.Date;

public class FareReceipt {

    private Long rideFareId;
    private double rideAmount;
    private LocalDate generatedAt;

    public FareReceipt(Long rideFareId, double rideAmount, LocalDate generatedAt) {
        this.rideFareId = rideFareId;
        this.rideAmount = rideAmount;
        this.generatedAt = generatedAt;
    }

    public Long getRideFareId() {
        return rideFareId;
    }

    public void setRideFareId(Long rideFareId) {
        this.rideFareId = rideFareId;
    }

    public double getRideAmount() {
        return rideAmount;
    }

    public void setRideAmount(double rideAmount) {
        this.rideAmount = rideAmount;
    }

    public LocalDate getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDate generatedAt) {
        this.generatedAt = generatedAt;
    }

    @Override
    public String toString() {
        return "FareReceipt{" +
                "rideFareId=" + rideFareId +
                ", rideAmount=" + rideAmount +
                ", generatedAt=" + generatedAt +
                '}';
    }
}
