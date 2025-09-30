import java.util.Scanner;

// Custom exception for invalid ride type
class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}

// Abstract class Ride
abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    protected double distance;

    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getDistance() {
        return distance;
    }

    // Abstract method
    public abstract double calculateFare();
}

// BikeRide subclass
class BikeRide extends Ride {
    public BikeRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 10;
    }
}

// CarRide subclass
class CarRide extends Ride {
    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 20;
    }
}

// Main class
public class RideSharingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Input
            String rideType = sc.nextLine().trim().toLowerCase();
            if (!sc.hasNextDouble()) {
                throw new IllegalArgumentException("Distance must be a number.");
            }
            double distance = sc.nextDouble();

            if (distance <= 0) {
                throw new IllegalArgumentException("Distance must be greater than 0.");
            }

            Ride ride;

            // Assign driver & vehicle (fixed for demo)
            if (rideType.equals("bike")) {
                ride = new BikeRide("Ravi Kumar", "RJ-27-BK-1234", distance);
            } else if (rideType.equals("car")) {
                ride = new CarRide("Suresh Mehta", "RJ-27-CR-5678", distance);
            } else {
                throw new InvalidRideTypeException("Invalid ride type: " + rideType);
            }

            // Output
            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.println("Distance: " + ride.getDistance() + " km");
            System.out.println("Fare: ₹" + ride.calculateFare());

        } catch (InvalidRideTypeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
