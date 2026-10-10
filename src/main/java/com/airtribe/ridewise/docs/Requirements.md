# Requirements – RideWise

## 1. Purpose

RideWise is a console-based learning project that models the core flow of a ride-sharing application. The goal is to practise OOP, SOLID principles, object relationships, and Strategy/Builder design patterns without implementing REST APIs or database persistence.

## 2. Functional requirements

### Rider management
- Create a rider with a name, email, and latitude/longitude.
- Generate a unique rider ID within the running application.
- Avoid adding another rider with an email already present; the current service returns the existing rider.
- Find a rider by ID and report an error if the ID does not exist.

### Driver management
- Create a driver with a name, email, location, availability, and vehicle type.
- Generate a driver ID within the running application.
- Reject a duplicate driver email.
- Retrieve drivers and identify available drivers.
- Track the number of rides associated with each driver using `rideCount`.

### Ride matching
- Accept a rider, requested vehicle type, and maximum search radius.
- Match only available drivers whose vehicle type matches the requested type.
- Use geographic coordinates to calculate straight-line distance in kilometres.
- Support at least two matching policies: nearest eligible driver and ride-count-based selection.
- If no eligible driver is found, report that a driver is unavailable.

### Ride lifecycle and fare
- Create a ride for a rider and selected driver.
- Record the ride ID, rider, driver, requested distance, and status.
- Mark a driver unavailable when a ride is assigned and available again when the ride is completed or cancelled.
- Calculate the fare using a replaceable fare strategy.
- Create a fare receipt when a ride is completed.
- Support the statuses `REQUESTED`, `ASSIGNED`, `COMPLETED`, and `CANCELLED` as model values.

## 3. Non-functional requirements

- Keep the implementation readable and suitable for LLD practice.
- Keep matching and fare policies replaceable without rewriting the ride service's core flow.
- Use meaningful domain classes and enums rather than loosely typed values.
- Keep the application runnable locally with a JDK and no external service dependency.

## 4. Constraints and assumptions

- Data is stored in memory and is not retained after the application exits.
- Distance means straight-line geographic distance, not road distance or travel time.
- The maximum search radius and fare constants are configured in `util/Constants.java`.
- The project is a console demonstration; authentication, payment processing, maps integration, persistence, and REST endpoints are out of scope.

## 5. Current implementation notes

The source code is a learning implementation and does not yet enforce every business rule completely. In particular, `RideService.requestRide()` increments `rideCount` when a driver is assigned, `cancelRide()` also increments it, and `completeRide()` does not increment it. If `rideCount` is intended to represent completed rides, this should be corrected so it increments once on a successful transition to `COMPLETED`. Also, `cancelRide()` should not count a cancelled ride as completed. `RideStatus.REQUESTED` exists, but the current request flow creates the ride directly with `ASSIGNED` status.
