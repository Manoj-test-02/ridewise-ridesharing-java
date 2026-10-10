package com.airtribe.ridewise.model;

public class Ride {

    private Long rideId;
    private Rider rider;
    private Driver driver;
    private Double distance;
    private RideStatus rideStatus;

    public Ride(BuilderRide  builderRide) {
        this.rideId = builderRide.rideId;
        this.rider = builderRide.rider;
        this.driver = builderRide.driver;
        this.distance = builderRide.distance;
        this.rideStatus = builderRide.rideStatus;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRide(Rider rider) {
        this.rider = rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }


    @Override
    public String toString() {
        return "Ride{" +
                "rideId=" + rideId +
                ", rider=" + rider +
                ", driver=" + driver +
                ", distance=" + distance +
                ", rideStatus=" + rideStatus +
                '}';
    }

    public static class BuilderRide {
        private Long rideId;
        private Rider rider;
        private Driver driver;
        private Double distance;
        private RideStatus rideStatus;

        public BuilderRide(Long rideId){
            this.rideId = rideId;
        }
        public BuilderRide rider(Rider rider) {
            this.rider = rider;
            return this;
        }
        public BuilderRide driver(Driver driver) {
            this.driver = driver;
            return this;
        }
        public BuilderRide distance(Double distance) {
            this.distance = distance;
            return this;
        }
        public BuilderRide rideStatus(RideStatus rideStatus) {
            this.rideStatus = rideStatus;
            return this;
        }


        public Ride build() {
            return new Ride(this);
        }
    }
}
