# Class Model – RideWise

This document describes the main classes and their responsibilities as implemented in the source.

## Domain model

### `Rider`
Represents a person requesting a ride.

- Fields: `riderId`, `riderName`, `riderMail`, `latitude`, `longitude`
- Responsibility: hold rider identity, contact, and current location data.

### `Driver`
Represents a driver who may be assigned to a ride.

- Fields: `driverId`, `driverName`, `driverMail`, `latitude`, `longitude`, `available`, `vehicleType`, `rideCount`
- Responsibility: hold driver profile, location, availability, vehicle category, and ride count.
- Provides `incrementRideCount()` and `decrementRideCount()` methods.

### `Ride`
Represents a ride assignment.

- Fields: `rideId`, `rider`, `driver`, `distance`, `rideStatus`
- Responsibility: connect the rider and assigned driver and track ride distance/status.
- Construction: nested `BuilderRide` builds a `Ride` by setting its values fluently.

### `FareReceipt`
Represents the fare result created when a ride is completed.

- Fields: `rideFareId`, `rideAmount`, `generatedAt`
- Responsibility: store the generated receipt ID, amount, and date.

### Enums

- `VehicleType`: `BIKE`, `AUTO`, `CAR`; each value has a fare cost.
- `RideStatus`: `REQUESTED`, `ASSIGNED`, `COMPLETED`, `CANCELLED`.

## Services

### `RiderService`
Creates riders, avoids duplicate rider emails by returning the existing rider, and retrieves a rider by ID. Rider records are kept in an in-memory list.

### `DriverService`
Creates drivers, rejects duplicate driver emails, returns driver lists, retrieves drivers, and manages availability. Driver records are kept in an in-memory list.

### `RideService`
Coordinates ride requests, driver selection, driver availability, ride storage, cancellation, completion, and fare receipt generation. It delegates matching and fare calculation to strategy interfaces.

## Strategy interfaces and implementations

### `RideMatchingStrategy`
Defines `findDriver(Rider, List<Driver>, VehicleType, double)`, returning one `Driver` or `null` if no eligible driver matches.

- `NearestDriverStrategy`: filters by availability, vehicle type, and radius, then returns the closest eligible driver.
- `LeastActiveDriverStrategy`: applies the same eligibility filters, then returns the driver with the highest `rideCount`. The behavior currently favours the most-used driver, so the class name does not match its implementation. Consider renaming it to `MostExperiencedDriverStrategy`, or change the comparator if the intended policy is to favour drivers with fewer rides.

### `FareStrategy`
Defines `calculateFare(Ride)`.

- `DefaultFareStrategy`: calculates base fare plus distance multiplied by the vehicle fare rate.
- `PeakHourFareStrategy`: applies the peak-hour multiplier to the distance-based portion of the fare.

## Utilities and exceptions

- `DistanceCalculator`: calculates straight-line distance in kilometres using the Haversine formula.
- `IdGenerator`: singleton that generates incrementing IDs for riders, drivers, rides, and fare receipts within the running process.
- `Constants`: stores base fare, peak-hour multiplier, and maximum matching radius.
- `RiderNotFoundException`, `DriverNotFoundException`: domain-specific runtime exceptions for missing rider/driver cases.

## High-level class view

```text
RideWiseApplication
 ├── RiderService ─────────────── Rider
 ├── DriverService ────────────── Driver ─── VehicleType
 └── RideService ──────────────── Ride ───── Rider
       │                           ├──────── Driver
       │                           └──────── RideStatus
       ├── RideMatchingStrategy
       │     ├── NearestDriverStrategy
       │     └── LeastActiveDriverStrategy
       └── FareStrategy
             ├── DefaultFareStrategy
             └── PeakHourFareStrategy

RideService ──creates──> FareReceipt
Utilities: DistanceCalculator, IdGenerator, Constants
```
