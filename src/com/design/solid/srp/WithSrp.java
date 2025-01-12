package com.design.solid.srp;

// Example applying SRP
class Employee {
    private String name;
    private String id;
    private double salary;

    public Employee(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    // Getter methods

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }
}

class BonusCalculator {
    // Method to calculate bonus
    public double calculateBonus(Employee employee) {
        return employee.getSalary() * 0.1; // Assume 10% bonus
    }
}

class DatabaseManager {
    // Method to save employee details to database
    public void saveToDatabase(Employee employee) {
        // Code to save employee details to database
        System.out.println("Saving employee details to database: " + employee.getName() + ", " + employee.getId() + ", " + employee.getSalary());
    }
}

public class WithSrp {
    
}
