package com.design.solid.srp;

// Example without applying SRP
class Employee {
    private String name;
    private String id;
    private double salary;

    public Employee(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    // Method to calculate bonus
    public double calculateBonus() {
        return salary * 0.1; // Assume 10% bonus
    }

    // Method to save employee details to database
    public void saveToDatabase() {
        // Code to save employee details to database
        System.out.println("Saving employee details to database: " + name + ", " + id + ", " + salary);
    }
}

public class WithoutSrp {
    
}

