package com.design.pattern.creational;

// It is a Creational Pattern
// Used when we want to build an object made up from other objects
// When we want to keep the creation of other object separate from main object
// Product Class
class Student {
    private final String rollNumber; // Mandatory
    private final String name;       // Optional
    private final int age;           // Optional
    private final String fatherName; // Optional
    private final String motherName; // Optional

    Student(StudentBuilder builder) {
        this.rollNumber = builder.rollNumber;
        this.name = builder.name;
        this.age = builder.age;
        this.fatherName = builder.fatherName;
        this.motherName = builder.motherName;
    }
}

// Builder Class
class StudentBuilder {
    final String rollNumber; // Mandatory
    String name;
    int age;
    String fatherName;
    String motherName;

    public StudentBuilder(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public StudentBuilder setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public StudentBuilder setMotherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public Student2 build() {
        return new Student2(this);
    }
}

// Director Class
class StudentDirector {
    private final StudentBuilder builder;

    public StudentDirector(StudentBuilder builder) {
        this.builder = builder;
    }

    public Student2 constructEngineeringStudent() {
        return builder
                .setName("John Doe")
                .setAge(20)
                .setFatherName("Mr. Doe")
                .build();
    }

    public Student2 constructMBAStudent() {
        return builder
                .setName("Jane Smith")
                .setAge(22)
                .setFatherName("Mr. Smith")
                .setMotherName("Mrs. Smith")
                .build();
    }
}

// Main Class
public class BuilderClient {
    public static void main(String[] args) {
        // Create a Director for Engineering Student
        StudentBuilder engineeringBuilder = new StudentBuilder("ENG001");
        StudentDirector engineeringDirector = new StudentDirector(engineeringBuilder);
        Student2 engineeringStudent = engineeringDirector.constructEngineeringStudent();

        // Create a Director for MBA Student
        StudentBuilder mbaBuilder = new StudentBuilder("MBA001");
        StudentDirector mbaDirector = new StudentDirector(mbaBuilder);
        Student2 mbaStudent = mbaDirector.constructMBAStudent();

        // Print the results
        System.out.println(engineeringStudent);
        System.out.println(mbaStudent);
    }
}