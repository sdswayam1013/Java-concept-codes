
import java.util.Scanner;
//runtime and compile time polymorphism//

class Vehicle {

    // Compile-time polymorphism: method overloading
    public void startEngine() {
        System.out.println("Starting vehicle engine in a standard way...");
    }

    public void startEngine(String keyType) {
        System.out.println("Starting engine using a " + keyType + " key...");
    }

    public void startEngine(int remoteDistance) {
        System.out.println("Starting engine remotely from " + remoteDistance + " meters away...");
    }

    // Runtime polymorphism: method overriding candidate
    public void drive() {
        System.out.println("Vehicle is driving...");
    }
}

class Car extends Vehicle {
    @Override
    public void drive() {
        System.out.println("Car is driving smoothly on the road 🚗");
    }
}

class Bike extends Vehicle {
    @Override
    public void drive() {
        System.out.println("Bike is racing down the street 🏍️");
    }
}

class Truck extends Vehicle {
    @Override
    public void drive() {
        System.out.println("Truck is carrying heavy load 🚚");
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Choose vehicle type (runtime)
        System.out.println("Choose a vehicle type:\n1. Car\n2. Bike\n3. Truck");
        int choice = sc.nextInt();

        Vehicle vehicle; // parent reference
        switch (choice) {
            case 1 -> vehicle = new Car();
            case 2 -> vehicle = new Bike();
            case 3 -> vehicle = new Truck();
            default -> {
                System.out.println("Invalid choice. Defaulting to Car.");
                vehicle = new Car();
            }
        }

        // Choose how to start engine (compile-time polymorphism)
        System.out.println("\nChoose how to start the engine:\n1. Normal start\n2. With key type\n3. With remote distance");
        int startChoice = sc.nextInt();
        sc.nextLine();

        switch (startChoice) {
            case 1 -> vehicle.startEngine(); // default
            case 2 -> {
                System.out.print("Enter key type (e.g., smart, manual): ");
                String key = sc.nextLine();
                vehicle.startEngine(key);
            }
            case 3 -> {
                System.out.print("Enter remote distance (in meters): ");
                int distance = sc.nextInt();
                vehicle.startEngine(distance);
            }
            default -> System.out.println("Invalid start option.");
        }

        // Runtime polymorphism (method overriding)
        System.out.println("\n-- Driving Action --");
        vehicle.drive();

        sc.close();
    }
}
