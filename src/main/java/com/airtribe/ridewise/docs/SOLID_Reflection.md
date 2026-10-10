# SOLID Reflection – RideWise

This project applies some SOLID ideas through small, replaceable components. The implementation is intentionally simple and is still a learning project; the notes below distinguish what is present from what could be improved.

## S — Single Responsibility Principle

**Applied:** `DistanceCalculator` handles distance calculations; `IdGenerator` generates IDs; `FareReceipt` represents receipt data; matching and fare policies have their own strategy classes.

**Could improve:** `RideService` currently coordinates ride assignment, availability changes, cancellation, completion, and receipt creation. This is acceptable for a small exercise, but these responsibilities could be separated if the project grows. Validation and state-transition rules should also be centralized rather than scattered.

## O — Open/Closed Principle

**Applied:** `RideMatchingStrategy` and `FareStrategy` allow alternative matching and fare policies to be added as implementations of interfaces. For example, the service can use `NearestDriverStrategy` or `LeastActiveDriverStrategy` without embedding the comparison logic directly in the service.

**Could improve:** `RideService` currently initializes concrete strategies directly (`NearestDriverStrategy` and `DefaultFareStrategy`). Constructor injection or setters would make it easier to change policies and test them without editing the service.

## L — Liskov Substitution Principle

**Applied:** Both driver-matching strategies implement `RideMatchingStrategy`; both fare strategies implement `FareStrategy`. They expose the same method contracts and can be substituted at the interface level.

**Could improve:** Keep the implementations consistent about edge cases. For example, both matching strategies should follow the same convention when there is no eligible driver (currently `null`) and should respect the same availability, vehicle, and radius constraints.

## I — Interface Segregation Principle

**Applied:** `RideMatchingStrategy` contains a focused driver-selection operation, and `FareStrategy` contains a focused fare-calculation operation. A fare strategy does not need to implement driver matching, and vice versa.

**Could improve:** Avoid adding unrelated ride lifecycle or data-storage operations to these interfaces. If more behavior is needed, use separate, narrowly scoped interfaces.

## D — Dependency Inversion Principle

**Partially applied:** `RideService` uses the `RideMatchingStrategy` and `FareStrategy` abstractions for its operations.

**Could improve:** `RideService` still creates concrete strategy instances internally and accesses service methods statically. Accept strategy and service dependencies through constructors (or another explicit configuration mechanism) to reduce coupling and make unit testing easier. The in-memory static lists also couple the services to one storage approach; repository interfaces would be a future option if persistence is introduced.

## Summary

The strongest SOLID examples in this project are the separate strategy interfaces and implementations, plus the focused distance and ID utilities. The main improvement would be dependency injection in `RideService`, followed by clearer ride-state transition rules and tests for matching and fare calculations. These changes are more useful at this stage than adding extra layers without a clear requirement.
