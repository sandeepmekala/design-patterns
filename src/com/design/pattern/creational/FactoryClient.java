package com.design.pattern.creational;

// Creational Pattern
// The Factory Pattern allows you to create objects without specifying the exact class of object that will be created 
// When a method returns one of several possible classes that share a common super class

// Product interface
interface Vehicle {
    void manufacture();
}

// Concrete Products
class Car implements Vehicle {
    @Override
    public void manufacture() {
        System.out.println("Car is being manufactured.");
    }
}

class Truck implements Vehicle {
    @Override
    public void manufacture() {
        System.out.println("Truck is being manufactured.");
    }
}

// Creator interface
interface VehicleFactory {
    Vehicle createVehicle();
}

// Concrete Creators
class CarFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}

class TruckFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Truck();
    }
}

// Client code
public class FactoryClient {
    public static void main(String[] args) {
        // Creating a car using CarFactory
        VehicleFactory carFactory = new CarFactory();
        Vehicle car = carFactory.createVehicle();
        car.manufacture();

        // Creating a truck using TruckFactory
        VehicleFactory truckFactory = new TruckFactory();
        Vehicle truck = truckFactory.createVehicle();
        truck.manufacture();
    }
}
