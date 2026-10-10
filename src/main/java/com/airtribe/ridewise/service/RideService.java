package com.airtribe.ridewise.service;

import com.airtribe.ridewise.exception.DriverNotFoundException;
import com.airtribe.ridewise.model.*;
import com.airtribe.ridewise.strategy.DefaultFareStrategy;
import com.airtribe.ridewise.strategy.FareStrategy;
import com.airtribe.ridewise.strategy.NearestDriverStrategy;
import com.airtribe.ridewise.strategy.RideMatchingStrategy;
import com.airtribe.ridewise.util.Constants;
import com.airtribe.ridewise.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RideService {
    private static List<Ride> rideDetails = new ArrayList<>();
    private static RiderService riderService = new RiderService();
    private static DriverService driverService = new DriverService();
    private static RideMatchingStrategy rideMatchingStrategy = new NearestDriverStrategy();
    private  static  FareStrategy fareStrategy = new DefaultFareStrategy();
    private static IdGenerator idGenerator = IdGenerator.getInstance();

    public static Ride requestRide(Long riderId, VehicleType vehicle, double distance){
        if(riderId == null){
            throw new IllegalArgumentException("rideId can not be null");
        }
        if(distance <= 0){
            throw new IllegalArgumentException("distance can not be negative");
        }
        Rider rider = riderService.getRiderById(riderId);
        Driver driver = rideMatchingStrategy.findDriver(rider,driverService.getDrivers(),vehicle, Constants.MAX_RADIUSKM);
        if(driver == null){
            throw new DriverNotFoundException("Driver Not Available at this Moment");
        }
        driver.setAvailable(false);
        driver.incrementRideCount();
        Ride newRide = new Ride.BuilderRide(idGenerator.generateRideId()).rider(rider).driver(driver).distance(distance).rideStatus(RideStatus.ASSIGNED).build();
        rideDetails.add(newRide);
        return newRide;
    }

    public static Ride cancelRide(Long rideId){
        if(rideId == null){
            throw new IllegalArgumentException("rideId can not be null");
        }
        for(Ride ride : rideDetails){
            if(ride.getRideId() ==  rideId){
                ride.setRideStatus(RideStatus.CANCELLED);
                ride.getDriver().setAvailable(true);
                ride.getDriver().incrementRideCount();
                System.out.println("Ride Cancelled Successfully");
                return ride;
            }
        }
        throw new IllegalArgumentException("Ride Not Available");
    }

    public static FareReceipt completeRide(Long rideId){
        if(rideId == null){
            throw new IllegalArgumentException("rideId can not be null");
        }
        for(Ride ride : rideDetails){
            if(ride.getRideId() ==  rideId){
                double totalFare = fareStrategy.calculateFare(ride);
                FareReceipt receipt = new FareReceipt(idGenerator.generateFareReceiptId(), totalFare, LocalDate.now());
                ride.getDriver().setAvailable(true);
                return receipt;

            }
        }
        throw new IllegalArgumentException("Ride Not Available");
    }

    public static List<Ride> getAllRides(){
        if(rideDetails == null){
            throw new IllegalArgumentException("No Active Ride Found");
        }
        return rideDetails;
    }
}
