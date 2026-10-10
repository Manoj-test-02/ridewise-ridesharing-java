package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.Ride;
import com.airtribe.ridewise.util.Constants;

public class PeakHourFareStrategy implements FareStrategy{
    public double calculateFare(Ride ride){
        return Constants.BASE_RATE + (ride.getDistance() * ride.getDriver().getVehicleType().getFareCost() * Constants.PEAK_HOUR_RATE);
    }
}
