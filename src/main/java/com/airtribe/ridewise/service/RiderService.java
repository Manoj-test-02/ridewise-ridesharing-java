package com.airtribe.ridewise.service;

import com.airtribe.ridewise.exception.RiderNotFoundException;
import com.airtribe.ridewise.model.Rider;
import com.airtribe.ridewise.util.IdGenerator;

import java.util.ArrayList;

public class RiderService {
private static final ArrayList<Rider> riders = new ArrayList<Rider>();
    private static final IdGenerator idGenerator = IdGenerator.getInstance();

public static Rider addNewRider(String riderName, String riderMail, double latitude, double longitude){
    for(Rider r: riders){
        if(r.getRiderMail().equals(riderMail)){
            System.out.println("Rider already exists");
            return r;
        }
    }
    Rider rider = new Rider(idGenerator.generateRiderId(), riderName, riderMail, latitude, longitude);
    riders.add(rider);
    System.out.println("Rider added Successfully");
    return rider;
}

public static Rider getRiderById(Long riderId){
    for(Rider r : riders){
        if(r.getRiderId() ==(riderId.longValue())){
            return r;
        }
    }
    throw  new RiderNotFoundException("Rider not found. Rider Id: " + riderId);
}

}
