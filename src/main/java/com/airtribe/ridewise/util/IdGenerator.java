package com.airtribe.ridewise.util;

public class IdGenerator {

    private static IdGenerator instance;
    private long driverId = 0;
    private long riderId = 0;
    private long rideId= 0;
    private long fareReceiptId =0;

    private IdGenerator() {}

    public static IdGenerator getInstance() {
        if (instance == null){
            synchronized (IdGenerator.class) {
                if(instance == null){
                    instance = new IdGenerator();
                }
            }
        }
        return instance;
    }

    public long generateDriverId(){
        return ++driverId;
    }
    public long generateRiderId(){
        return ++riderId;
    }
    public long generateRideId(){return ++rideId;}
    public long generateFareReceiptId(){return ++fareReceiptId;}

}
