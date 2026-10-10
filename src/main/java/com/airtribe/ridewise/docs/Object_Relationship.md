# Object Relationships – RideWise

This document explains how the main objects refer to or use one another.

## Domain relationships

### Rider and Ride
- A `Ride` holds a reference to one `Rider`.
- A rider can participate in multiple rides over time.
- The current `Rider` class does not maintain a collection of rides; the ride-to-rider reference is stored on `Ride`.

**Relationship:** one `Rider` to many `Ride` records over time; each `Ride` refers to one rider.

### Driver and Ride
- A `Ride` holds a reference to the assigned `Driver`.
- A driver can be assigned to multiple rides over time, though availability is used to prevent assigning a driver already marked unavailable.
- The current `Driver` class does not maintain a collection of rides; rides refer to the driver.

**Relationship:** one `Driver` to many `Ride` records over time; each assigned `Ride` refers to one driver.

### Ride and FareReceipt
- `RideService.completeRide()` calculates the fare and creates a `FareReceipt`.
- `FareReceipt` does not currently hold a reference to `Ride`, and `Ride` does not store a receipt field. The service returns the receipt to the caller.

**Relationship in current code:** creation/use association, not a stored object reference. Conceptually, a receipt is produced for a completed ride, but that relationship is not persisted in the model.

## Service relationships

- `RideService` uses `RiderService` to find the rider and `DriverService` to obtain candidate drivers.
- `RideService` delegates driver selection to `RideMatchingStrategy`.
- `RideService` delegates fare calculation to `FareStrategy`.
- `NearestDriverStrategy` and `LeastActiveDriverStrategy` use `DistanceCalculator` to enforce the search radius.
- `RiderService` and `DriverService` use the singleton `IdGenerator` to generate IDs.

## Relationship diagram

```text
Rider 1 <──────── 0..* Ride 0..* ────────> 1 Driver
                     │
                     └── RideService creates/returns FareReceipt
                         (receipt is not stored on Ride in current code)

RideService ──uses──> RiderService
RideService ──uses──> DriverService
RideService ──uses──> RideMatchingStrategy
RideService ──uses──> FareStrategy
RideMatchingStrategy implementations ──uses──> DistanceCalculator
RiderService / DriverService ──uses──> IdGenerator
```

## Important lifecycle note

`RideService.requestRide()` marks the chosen driver unavailable and creates a ride with `ASSIGNED` status. `completeRide()` makes the driver available again and returns a receipt. `cancelRide()` marks the ride cancelled and makes the driver available again. The current code should be tightened so ride status transitions are validated and a completed/cancelled ride cannot be processed repeatedly. Also, `rideCount` is currently incremented on assignment and cancellation, not exclusively on completion.
