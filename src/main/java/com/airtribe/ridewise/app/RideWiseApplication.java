package com.airtribe.ridewise.app;

import com.airtribe.ridewise.model.*;
import com.airtribe.ridewise.service.DriverService;
import com.airtribe.ridewise.service.RideService;
import com.airtribe.ridewise.service.RiderService;
import com.airtribe.ridewise.util.IdGenerator;

public class RideWiseApplication {
    private static final RiderService RIDERSERVICE = new RiderService();
    private static final DriverService DRIVERSERVICE = new DriverService();
    private static final RideService RIDESERVICE = new RideService();
    private static IdGenerator idGenerator;

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("Welcome to Ride Wise Application");
        System.out.println("================================");

        System.out.println("");
        System.out.println("---------------------------------");
        System.out.println("---------Rider Services----------");
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println("---------Add Rider---------------");
        Rider rider1 = RIDERSERVICE.addNewRider("Manoj", "test@test.com", 11.0168, 76.9558);
        System.out.println(rider1.toString());
        Rider rider2 = RIDERSERVICE.addNewRider("Arun", "arun@test.com",11.0055, 76.9666);
        System.out.println(rider2.toString());
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println("---------Find Rider--------------");
        Rider foundRider = RIDERSERVICE.getRiderById(1L);
        System.out.println("Found Rider: " + foundRider.toString());
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println("---------------------------------");
        System.out.println("---------Driver Services---------");
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println("---------Add Driver--------------");
        Driver driver1 = DRIVERSERVICE.addNewDriver("driver1", "driver@test.com",11.0180,
                76.9570, true, VehicleType.BIKE);
        System.out.println(driver1.toString());
        Driver driver2 = DRIVERSERVICE.addNewDriver("driver2", "driver2@test.com",11.0300,
        76.9700, true, VehicleType.AUTO);
        System.out.println(driver2.toString());
        Driver driver3 = DRIVERSERVICE.addNewDriver("driver3", "driver3@test.com",11.0070,
                76.9680, true, VehicleType.BIKE);
        System.out.println(driver3.toString());
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println("---------------------------------");
        System.out.println("---------Ride Services-----------");
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println("---------Request Ride------------");
        Ride ride1 = RIDESERVICE.requestRide(1L, VehicleType.BIKE, 5);
        System.out.println(ride1.toString());
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println("---------Complete Ride------------");
        FareReceipt receipt = RIDESERVICE.completeRide(1L);
        System.out.println(receipt.toString());
        System.out.println("---------------------------------");


    }
}
