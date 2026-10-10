package com.airtribe.ridewise.service;

import com.airtribe.ridewise.exception.DriverNotFoundException;
import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.Rider;
import com.airtribe.ridewise.model.VehicleType;
import com.airtribe.ridewise.util.IdGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverService {

    private static final List<Driver> drivers = new ArrayList<>();
    private static final IdGenerator idGenerator = IdGenerator.getInstance();

    public static Driver addNewDriver(String driverName, String driverMail, double latitude,
                             double longitude, boolean available, VehicleType vehicleType){
        for(Driver d : drivers){
            if(d.getDriverMail().equals(driverMail)) {
                throw new RuntimeException("Driver already part of our Family!!");
            }
        }
        Driver driver = new Driver(idGenerator.generateDriverId(), driverName, driverMail, latitude, longitude, available, vehicleType);
        drivers.add(driver);
        System.out.println("Driver added Successfully");
        return driver;
    }

    public static List<Driver> getAvailableDrivers(){
        List<Driver> availableDriver = new ArrayList<>();
       for(Driver d : drivers){
           if(d.isAvailable()){
               availableDriver.add(d);
           }
       }

        if(availableDriver.isEmpty()){
            throw new DriverNotFoundException("Driver Not Available. We will try to get the available Drivers");
        }
        return availableDriver;
    }

    public static void updateDriverStatus(Long driverId, boolean availability){
        for(Driver d : drivers){
            if(d.getDriverId() == driverId){
                d.setAvailable(availability);
            }
        }
        throw new DriverNotFoundException("Driver with id " + driverId + " not found");
    }

    public static Driver getDriverById(Long driverId){
        for(Driver d : drivers){
            if(d.getDriverId() == driverId){
                return d;
            }
        }
        throw new DriverNotFoundException("Driver with id " + driverId + " not found");
    }

    public static List<Driver> getDrivers(){
        if(drivers.isEmpty()){
            throw new DriverNotFoundException("Driver Not Available");
        }
        return drivers;
    }
}
