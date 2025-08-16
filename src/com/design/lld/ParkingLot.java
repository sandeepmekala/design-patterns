package com.design.lld;

import java.util.*;
import java.time.LocalDateTime;
import java.time.Duration;

enum VehicleType {
    TWO_WHEELER, FOUR_WHEELER
}

class Vehicle {
    private int vehicleNo;
    private VehicleType vehicleType;

    public Vehicle(int vehicleNo, VehicleType vehicleType) {
    this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
    }

    public int getVehicleNo() {
        return vehicleNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}

abstract class ParkingSpot {
    private int id;
    private boolean isEmpty = true;
    private Vehicle vehicle;

    public ParkingSpot(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isEmpty = false;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isEmpty = true;
    }

    public abstract int getPrice();
}

class TwoWheelerSpot extends ParkingSpot {
    public TwoWheelerSpot(int id) {
        super(id);
    }

    @Override
    public int getPrice() {
        return 10; // Price for two-wheeler parking
    }
}

class FourWheelerSpot extends ParkingSpot {
    public FourWheelerSpot(int id) {
        super(id);
    }

    @Override
    public int getPrice() {
        return 20; // Price for four-wheeler parking
    }
}

class ParkingSpotManager {
    private List<ParkingSpot> parkingSpots;

    public ParkingSpotManager(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public ParkingSpot findParkingSpace() {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isEmpty()) {
                return spot;
            }
        }
        return null; // No space available
    }

    public void parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findParkingSpace();
        if (spot != null) {
            spot.parkVehicle(vehicle);
        } else {
            throw new IllegalStateException("No available parking spot.");
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isEmpty() && spot.getId() == vehicle.getVehicleNo()) {
                spot.removeVehicle();
                return;
            }
        }
        throw new IllegalStateException("Vehicle not found.");
    }
}

class ParkingSpotManagerFactory {
    public ParkingSpotManager getParkingSpotManager(Vehicle vehicle, List<ParkingSpot> twoWheelerSpots, List<ParkingSpot> fourWheelerSpots) {
        if (vehicle.getVehicleType() == VehicleType.TWO_WHEELER) {
            return new ParkingSpotManager(twoWheelerSpots);
        } else {
            return new ParkingSpotManager(fourWheelerSpots);
        }
    }
}

class Ticket {
    private LocalDateTime entryTime;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.entryTime = LocalDateTime.now();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}

class EntranceGate {
    private ParkingSpotManagerFactory factory;

    public EntranceGate(ParkingSpotManagerFactory factory) {
        this.factory = factory;
    }

    public Ticket generateTicket(Vehicle vehicle, ParkingSpotManager manager) {
        ParkingSpot spot = manager.findParkingSpace();
        if (spot == null) {
            throw new IllegalStateException("No available parking spot.");
        }
        manager.parkVehicle(vehicle);
        return new Ticket(vehicle, spot);
    }
}

class ExitGate {
    private ParkingSpotManagerFactory factory;

    public ExitGate(ParkingSpotManagerFactory factory) {
        this.factory = factory;
    }

    public int calculatePrice(Ticket ticket) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        return (int) (minutes * ticket.getParkingSpot().getPrice());
    }

    public void removeVehicle(Ticket ticket, ParkingSpotManager manager) {
        manager.removeVehicle(ticket.getVehicle());
    }
}

public class ParkingLot {
    public static void main(String[] args) {
        List<ParkingSpot> twoWheelerSpots = new ArrayList<>();
        List<ParkingSpot> fourWheelerSpots = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            twoWheelerSpots.add(new TwoWheelerSpot(i));
            fourWheelerSpots.add(new FourWheelerSpot(i + 5));
        }

        ParkingSpotManagerFactory factory = new ParkingSpotManagerFactory();

        EntranceGate entranceGate = new EntranceGate(factory);
        ExitGate exitGate = new ExitGate(factory);

        // Example Usage
        Vehicle vehicle1 = new Vehicle(101, VehicleType.TWO_WHEELER);
        ParkingSpotManager manager = factory.getParkingSpotManager(vehicle1, twoWheelerSpots, fourWheelerSpots);

        // Vehicle enters
        Ticket ticket1 = entranceGate.generateTicket(vehicle1, manager);

        // Calculate price and exit
        int price = exitGate.calculatePrice(ticket1);
        System.out.println("Price for parking: " + price);

        exitGate.removeVehicle(ticket1, manager);
        System.out.println("Vehicle exited.");
    }
}
