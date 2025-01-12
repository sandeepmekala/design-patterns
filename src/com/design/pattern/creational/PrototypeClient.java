package com.design.pattern.creational;

// It is a Creational Pattern
// Used when we need to create new object by cloning(copying) other object
// Prototype Interface
interface Prototype {
    Prototype clone();
}

// Student Class implementing Prototype
class Student2 implements Prototype {
    private int age;
    private int rollNumber;
    private String name;

    // Constructor
    public Student2(int age, int rollNumber, String name) {
        this.age = age;
        this.rollNumber = rollNumber;
        this.name = name;
    }

    // Implementing the clone method
    @Override
    public Student2 clone() {
        return new Student2(this.age, this.rollNumber, this.name);
    }
}

// Main Class to Test
public class PrototypeClient {
    public static void main(String[] args) {
        // Original Object
        Student2 originalStudent = new Student2(20, 101, "John Doe");

        // Cloning
        Student2 clonedStudent = originalStudent.clone();

        // Modifying clone
        // clonedStudent.name = "Jane Doe";

        // Printing Objects
        System.out.println("Original Student: " + originalStudent);
        System.out.println("Cloned Student: " + clonedStudent);
    }
}