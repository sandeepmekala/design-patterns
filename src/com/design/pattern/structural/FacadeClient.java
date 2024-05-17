package com.design.pattern.structural;

// It is a Structural pattern
// It is a refactoring pattern
// Make code clean
// Create simplified interface for lot of actions
// In this example only deposit and withdraw are exposed to customer but behind the scenes we perform many actions to validate public class FacadeClient {
// Subsystem class 1
class Engine {
    void start() {
        System.out.println("Engine started");
    }
    
    void stop() {
        System.out.println("Engine stopped");
    }
}

// Subsystem class 2
class AirConditioner {
    void turnOn() {
        System.out.println("Air conditioner turned on");
    }
    
    void turnOff() {
        System.out.println("Air conditioner turned off");
    }
}

// Subsystem class 3
class MusicPlayer {
    void playMusic() {
        System.out.println("Music playing");
    }
    
    void stopMusic() {
        System.out.println("Music stopped");
    }
}

// Facade class
class CarFacade {
    private Engine engine;
    private AirConditioner airConditioner;
    private MusicPlayer musicPlayer;

    public CarFacade() {
        this.engine = new Engine();
        this.airConditioner = new AirConditioner();
        this.musicPlayer = new MusicPlayer();
    }

    // Facade methods
    void startCar() {
        engine.start();
        airConditioner.turnOn();
        musicPlayer.playMusic();
    }
    
    void stopCar() {
        engine.stop();
        airConditioner.turnOff();
        musicPlayer.stopMusic();
    }
}

// Client - Car driver
public class FacadeClient {
    public static void main(String[] args) {
        CarFacade carFacade = new CarFacade();

        // Start the car
        carFacade.startCar();
        System.out.println("Driving...");

        // Stop the car
        carFacade.stopCar();
    }
}
