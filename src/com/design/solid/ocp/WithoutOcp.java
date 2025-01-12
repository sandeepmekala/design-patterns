package com.design.solid.ocp;

// Example without applying OCP
class Shape {
    private String type;

    public Shape(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public double calculateArea() {
        if (type.equals("circle")) {
            // Calculate area of circle
            return Math.PI * Math.pow(5, 2); // Assume radius = 5
        } else if (type.equals("rectangle")) {
            // Calculate area of rectangle
            return 5 * 10; // Assume length = 5, width = 10
        }
        return 0;
    }
}

public class WithoutOcp {
    
}
