package com.design.pattern.creational;

// It is a Creational Pattern
// Used when we need to create new object by cloning(copying) other object
public class PrototypeClient {
    
	public static void main(String[] args) {
        // Create prototypes for car and motorcycle
        CarPrototype carPrototype = new CarPrototype("Toyota Camry", "Red");
        MotorcyclePrototype motorcyclePrototype = new MotorcyclePrototype("Honda", "Sport");

        // Clone car prototype and modify the cloned instance
        CarPrototype clonedCar = (CarPrototype) carPrototype.clone();
        clonedCar.setColor("Blue");

        // Clone motorcycle prototype and modify the cloned instance
        MotorcyclePrototype clonedMotorcycle = (MotorcyclePrototype) motorcyclePrototype.clone();
        clonedMotorcycle.setType("Cruiser");

        // Print details of the original and modified prototypes
        System.out.println("Original Car Prototype: " + carPrototype.getModel() + " - " + carPrototype.getColor());
        System.out.println("Cloned Car Prototype: " + clonedCar.getModel() + " - " + clonedCar.getColor());

        System.out.println("Original Motorcycle Prototype: " + motorcyclePrototype.getBrand() + " - " + motorcyclePrototype.getType());
        System.out.println("Cloned Motorcycle Prototype: " + clonedMotorcycle.getBrand() + " - " + clonedMotorcycle.getType());
    }
}
// Prototype interface
interface VehiclePrototype {
    VehiclePrototype clone();
}

// Concrete prototype for a car
class CarPrototype implements VehiclePrototype {
    private String model;
    private String color;

    public CarPrototype(String model, String color) {
        this.model = model;
        this.color = color;
    }

    // Deep copy constructor
    public CarPrototype(CarPrototype prototype) {
        this.model = prototype.model;
        this.color = prototype.color;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public VehiclePrototype clone() {
        return new CarPrototype(this);
    }
}

// Concrete prototype for a motorcycle
class MotorcyclePrototype implements VehiclePrototype {
    private String brand;
    private String type;

    public MotorcyclePrototype(String brand, String type) {
        this.brand = brand;
        this.type = type;
    }

    // Deep copy constructor
    public MotorcyclePrototype(MotorcyclePrototype prototype) {
        this.brand = prototype.brand;
        this.type = prototype.type;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public VehiclePrototype clone() {
        return new MotorcyclePrototype(this);
    }
}