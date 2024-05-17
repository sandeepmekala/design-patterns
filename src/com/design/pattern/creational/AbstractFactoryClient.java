package com.design.pattern.creational;

import java.io.IOException;

// It is a Creational Pattern
// It is like a factory pattern, but everything is encapsulated, the methods that create the object, the factories that create the objects, the final objects. 
// The final objects contains objects that use strategy pattern

// Abstract Product A
interface Chair {
    void sitOn();
}

// Concrete Product A1
class WoodenChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a wooden chair.");
    }
}

// Concrete Product A2
class PlasticChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a plastic chair.");
    }
}

// Abstract Product B
interface Table {
    void placeOn();
}

// Concrete Product B1
class WoodenTable implements Table {
    @Override
    public void placeOn() {
        System.out.println("Placing something on a wooden table.");
    }
}

// Concrete Product B2
class PlasticTable implements Table {
    @Override
    public void placeOn() {
        System.out.println("Placing something on a plastic table.");
    }
}

// Abstract Factory
interface FurnitureFactory {
    Chair createChair();
    Table createTable();
}

// Concrete Factory 1
class WoodenFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new WoodenChair();
    }

    @Override
    public Table createTable() {
        return new WoodenTable();
    }
}

// Concrete Factory 2
class PlasticFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new PlasticChair();
    }

    @Override
    public Table createTable() {
        return new PlasticTable();
    }
}

// Client
public class AbstractFactoryClient {
    public static void main(String[] args) {
        // Create a wooden furniture factory
        FurnitureFactory woodenFactory = new WoodenFurnitureFactory();

        // Create products using the wooden factory
        Chair woodenChair = woodenFactory.createChair();
        Table woodenTable = woodenFactory.createTable();

        // Use the products
        woodenChair.sitOn();
        woodenTable.placeOn();

        // Create a plastic furniture factory
        FurnitureFactory plasticFactory = new PlasticFurnitureFactory();

        // Create products using the plastic factory
        Chair plasticChair = plasticFactory.createChair();
        Table plasticTable = plasticFactory.createTable();

        // Use the products
        plasticChair.sitOn();
        plasticTable.placeOn();
    }
}
