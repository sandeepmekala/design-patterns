package com.design.pattern.creational;

// It is a Creational Pattern
// Used when we want to build an object made up from other objects
// When we want to keep the creation of other object separate from main object
public class BuilderClient {
	public static void main(String[] args) {
        // Create a builder for desktop
        ComputerBuilder desktopBuilder = new DesktopBuilder();
        // Build a desktop computer
        Computer desktop = desktopBuilder.setCPU("Intel Core i5").setRAM(8).setStorage(256).build();
        // Use the desktop computer
        System.out.println("Desktop configuration:");
        System.out.println("Type: " + desktop.getType());
        System.out.println("CPU: " + desktop.getCPU());
        System.out.println("RAM: " + desktop.getRAM() + "GB");
        System.out.println("Storage: " + desktop.getStorage() + "GB");

        // Create a builder for laptop
        ComputerBuilder laptopBuilder = new LaptopBuilder();
        // Alternatively, use a director to construct the laptop
        // ComputerDirector director = new ComputerDirector(laptopBuilder);
        // Computer laptop = director.construct();

        // Build a laptop computer
        Computer laptop = laptopBuilder.setCPU("Intel Core i9").setRAM(32).setStorage(1024).build();
        // Use the laptop computer
        System.out.println("\nLaptop configuration:");
        System.out.println("Type: " + laptop.getType());
        System.out.println("CPU: " + laptop.getCPU());
        System.out.println("RAM: " + laptop.getRAM() + "GB");
        System.out.println("Storage: " + laptop.getStorage() + "GB");
    }
}

// Product class representing a Computer
class Computer {
    private String type; // Desktop or Laptop
    private String CPU;
    private int RAM; // in GB
    private int storage; // in GB

    public Computer(String type, String CPU, int RAM, int storage) {
        this.type = type;
        this.CPU = CPU;
        this.RAM = RAM;
        this.storage = storage;
    }

    // Getters for parts
    public String getType() {
        return type;
    }

    public String getCPU() {
        return CPU;
    }

    public int getRAM() {
        return RAM;
    }

    public int getStorage() {
        return storage;
    }
}

// Builder interface
interface ComputerBuilder {
    ComputerBuilder setType(String type);
    ComputerBuilder setCPU(String CPU);
    ComputerBuilder setRAM(int RAM);
    ComputerBuilder setStorage(int storage);
    Computer build();
}

// Concrete builder for Desktop computers
class DesktopBuilder implements ComputerBuilder {
    private String type = "Desktop";
    private String CPU;
    private int RAM;
    private int storage;

    @Override
    public ComputerBuilder setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public ComputerBuilder setCPU(String CPU) {
        this.CPU = CPU;
        return this;
    }

    @Override
    public ComputerBuilder setRAM(int RAM) {
        this.RAM = RAM;
        return this;
    }

    @Override
    public ComputerBuilder setStorage(int storage) {
        this.storage = storage;
        return this;
    }

    @Override
    public Computer build() {
        return new Computer(type, CPU, RAM, storage);
    }
}

// Concrete builder for Laptop computers
class LaptopBuilder implements ComputerBuilder {
    private String type = "Laptop";
    private String CPU;
    private int RAM;
    private int storage;

    @Override
    public ComputerBuilder setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public ComputerBuilder setCPU(String CPU) {
        this.CPU = CPU;
        return this;
    }

    @Override
    public ComputerBuilder setRAM(int RAM) {
        this.RAM = RAM;
        return this;
    }

    @Override
    public ComputerBuilder setStorage(int storage) {
        this.storage = storage;
        return this;
    }

    @Override
    public Computer build() {
        return new Computer(type, CPU, RAM, storage);
    }
}

// Director class (optional)
class ComputerDirector {
    private ComputerBuilder builder;

    public ComputerDirector(ComputerBuilder builder) {
        this.builder = builder;
    }

    public Computer construct() {
        // Define the construction process here if needed
        return builder.setType("Laptop").setCPU("Intel Core i7").setRAM(16).setStorage(512).build();
    }
}