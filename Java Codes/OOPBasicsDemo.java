// Demonstrates all 4 pillars of OOP: Encapsulation, Abstraction, Inheritance, Polymorphism
// Create a simple program to manage different types of vehicles (like cars and bikes).
// Each vehicle should have:
// A brand and speed (encapsulation)
// A method to display details (abstraction)
// Car and Bike should inherit from Vehicle (inheritance)
// Each should override the display method (polymorphism)//
// Abstraction - Abstract class hides internal details
abstract class Vehicle {
    // Encapsulation - private fields with getters/setters
    private String brand;
    private int speed;

    public Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // abstract method - to be implemented by subclasses
    public abstract void displayInfo();
}

// Inheritance - Car inherits from Vehicle
class Car extends Vehicle {
    private int doors;

    public Car(String brand, int speed, int doors) {
        super(brand, speed);
        this.doors = doors;
    }

    //  Polymorphism - overriding abstract method
    @Override
    public void displayInfo() {
        System.out.println("🚗 Car Brand: " + getBrand() + ", Speed: " + getSpeed() + " km/h, Doors: " + doors);
    }
}

// Another subclass
class Bike extends Vehicle {
    private boolean hasGear;

    public Bike(String brand, int speed, boolean hasGear) {
        super(brand, speed);
        this.hasGear = hasGear;
    }

    @Override
    public void displayInfo() {
        System.out.println("🏍️ Bike Brand: " + getBrand() + ", Speed: " + getSpeed() + " km/h, Has Gear: " + hasGear);
    }
}

// Main class to run the program
public class OOPBasicsDemo {
    public static void main(String[] args) {
        Vehicle car = new Car("Toyota", 180, 4);
        Vehicle bike = new Bike("Yamaha", 120, true);

        // Polymorphism in action: the right displayInfo() executes at runtime
        car.displayInfo();
        bike.displayInfo();
    }
}
