package com.design.pattern.structural;

// Step 1: Define the Abstraction and Implementor Interfaces

// Implementor Interface
 interface BreatheImplementer {
    void breathe();
}

// Abstraction Class
abstract class LivingThing {
    protected BreatheImplementer breatheImplementer;

    public LivingThing(BreatheImplementer breatheImplementer) {
        this.breatheImplementer = breatheImplementer;
    }

    public abstract void breatheProcess();
}
// Step 2: Create Concrete Implementors
// Concrete Implementors
class LandBreathe implements BreatheImplementer {
    @Override
    public void breathe() {
        System.out.println("Breathe through nose: Inhale oxygen, exhale carbon dioxide.");
    }
}

class WaterBreathe implements BreatheImplementer {
    @Override
    public void breathe() {
        System.out.println("Breathe through gills: Absorb oxygen from water, release carbon dioxide.");
    }
}

class TreeBreathe implements BreatheImplementer {
    @Override
    public void breathe() {
        System.out.println("Breathe through leaves: Inhale carbon dioxide, exhale oxygen.");
    }
}

// Step 3: Create Refined Abstraction
// Refined Abstraction Classes
class Dog extends LivingThing {
    public Dog(BreatheImplementer breatheImplementer) {
        super(breatheImplementer);
    }

    @Override
    public void breatheProcess() {
        System.out.print("Dog: ");
        breatheImplementer.breathe();
    }
}

class Fish extends LivingThing {
    public Fish(BreatheImplementer breatheImplementer) {
        super(breatheImplementer);
    }

    @Override
    public void breatheProcess() {
        System.out.print("Fish: ");
        breatheImplementer.breathe();
    }
}

class Tree extends LivingThing {
    public Tree(BreatheImplementer breatheImplementer) {
        super(breatheImplementer);
    }

    @Override
    public void breatheProcess() {
        System.out.print("Tree: ");
        breatheImplementer.breathe();
    }
}

// Step 4: Client Code
public class BridgeClient {
    public static void main(String[] args) {
        // Create Implementors
        BreatheImplementer landBreathe = new LandBreathe();
        BreatheImplementer waterBreathe = new WaterBreathe();
        BreatheImplementer treeBreathe = new TreeBreathe();

        // Create LivingThing objects with specific breathing implementations
        LivingThing dog = new Dog(landBreathe);
        LivingThing fish = new Fish(waterBreathe);
        LivingThing tree = new Tree(treeBreathe);

        // Call breatheProcess
        dog.breatheProcess();  // Output: Dog: Breathe through nose: Inhale oxygen, exhale carbon dioxide.
        fish.breatheProcess(); // Output: Fish: Breathe through gills: Absorb oxygen from water, release carbon dioxide.
        tree.breatheProcess(); // Output: Tree: Breathe through leaves: Inhale carbon dioxide, exhale oxygen.
    }
}


