# RideWise – Java Ride-Sharing LLD

RideWise is a small, console-based Java project for practising object-oriented design and low-level design (LLD) for a ride-sharing application. It focuses on models, services, driver matching, fare calculation, and ride lifecycle handling. It does not expose REST APIs or use a database; the current data is held in in-memory collections.

## Features

- Register riders and drivers with generated IDs.
- Store rider and driver latitude/longitude coordinates.
- Track driver availability and vehicle type (`BIKE`, `AUTO`, `CAR`).
- Find an available driver of the requested vehicle type within a configured radius.
- Support nearest-driver and ride-count-based matching strategies.
- Calculate fares through interchangeable fare strategies.
- Request, complete, and cancel rides, and create a fare receipt on completion.

## Project structure

```text
src/main/java/com/airtribe/ridewise/
├── app/          # Console entry point and sample flow
├── exception/    # Rider/driver lookup exceptions
├── model/        # Rider, Driver, Ride, FareReceipt, enums
├── service/      # Rider, Driver, and Ride operations
├── strategy/     # Driver-matching and fare-calculation strategies
└── util/         # Distance calculation, IDs, and constants
```

## Requirements

- JDK 17 or newer is recommended (the source uses modern Java collection APIs such as `Stream.toList()`).
- IntelliJ IDEA, Eclipse, or another Java IDE; alternatively, use the Java compiler and launcher.
- No external libraries are required by the source code.

## Run the application

Open the project in your IDE and run:

`com.airtribe.ridewise.app.RideWiseApplication`

Or compile and run from the repository root with a JDK installed:

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out com.airtribe.ridewise.app.RideWiseApplication
```

On Windows PowerShell, if the `find` command is unavailable or behaves differently, compile the Java files through the IDE or use a PowerShell file list with `javac`.

The application runs a sample flow: create two riders, look up a rider, add drivers, request a ride, and complete the ride.

## Configuration

`util/Constants.java` defines the base fare, peak-hour multiplier, and maximum driver search radius. `model/VehicleType.java` defines vehicle fare rates.

## Design notes and current scope

- Rider, driver, and ride data are stored in static in-memory collections. Data is lost when the application exits.
- `NearestDriverStrategy` selects the closest eligible driver. `LeastActiveDriverStrategy` currently selects the eligible driver with the greatest `rideCount`, despite the potentially misleading class name.
- The project is a learning/demo implementation, not a production dispatch system. Persistence, concurrency control, input validation, and automated tests are not currently provided.
- Before extending the project, ensure ride counts are updated only when the business event intended by the requirement occurs. In the current `RideService`, the count is incremented when a ride is assigned and when it is cancelled, rather than only after a completed ride.

## **Related Documents**

- [Requirements.md](src/main/java/com/airtribe/ridewise/docs/Requirements.md)
- [Class_Model.md](src/main/java/com/airtribe/ridewise/docs/Class_Model.md)
- [SOLID_Reflection.md](src/main/java/com/airtribe/ridewise/docs/SOLID_Reflection.md)
- [Object_Relationship.md](src/main/java/com/airtribe/ridewise/docs/Object_Relationship.md) 

