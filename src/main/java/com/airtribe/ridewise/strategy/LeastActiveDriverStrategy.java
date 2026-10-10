package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.Rider;
import com.airtribe.ridewise.model.VehicleType;
import com.airtribe.ridewise.util.DistanceCalculator;

import java.util.Comparator;
import java.util.List;

public class LeastActiveDriverStrategy implements RideMatchingStrategy{

    public Driver findDriver(Rider rider, List<Driver> drivers, VehicleType vehicleType, double maxDistance) {
        return drivers.stream()
                .filter(Driver::isAvailable)
                .filter(driver -> driver.getVehicleType() == vehicleType)
                .filter(driver ->
                        DistanceCalculator.calculateDistance(
                                rider.getLatitude(),
                                rider.getLongitude(),
                                driver.getLatitude(),
                                driver.getLongitude()
                        ) <= maxDistance)
                .max(Comparator.comparingInt(Driver::getRideCount))
                .orElse(null);
    }
}
