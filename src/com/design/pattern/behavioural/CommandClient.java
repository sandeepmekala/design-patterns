package com.design.pattern.behavioural;

import java.util.ArrayList;

// It is a Behavioral pattern
// In this an object is used to represent and encapsulate all the information needed to call a method at later time
// This information includes method name, object that owns this method and and parameter values
// Receiver - AirConditioner
class AirConditioner {
    private boolean isOn;
    private int temperature;

    public void turnOnAC() {
        this.isOn = true;
        System.out.println("Air Conditioner is ON");
    }

    public void turnOffAC() {
        this.isOn = false;
        System.out.println("Air Conditioner is OFF");
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Temperature set to " + temperature);
    }
}

// Command Interface
interface Command {
    void execute();
    void undo();
}

// Concrete Command - TurnACOnCommand
class TurnACOnCommand implements Command {
    private AirConditioner airConditioner;

    public TurnACOnCommand(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void execute() {
        airConditioner.turnOnAC();
    }

    @Override
    public void undo() {
        airConditioner.turnOffAC();
    }
}

// Concrete Command - TurnACOffCommand
class TurnACOffCommand implements Command {
    private AirConditioner airConditioner;

    public TurnACOffCommand(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void execute() {
        airConditioner.turnOffAC();
    }

    @Override
    public void undo() {
        airConditioner.turnOnAC();
    }
}

// Invoker - RemoteControl
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

    public void pressUndo() {
        command.undo();
    }
}

// Client
public class CommandClient {
    public static void main(String[] args) {
        AirConditioner ac = new AirConditioner();
        
        Command turnOnAC = new TurnACOnCommand(ac);
        Command turnOffAC = new TurnACOffCommand(ac);
        
        RemoteControl remote = new RemoteControl();
        
        // Turn on AC
        remote.setCommand(turnOnAC);
        remote.pressButton();
        
        // Turn off AC
        remote.setCommand(turnOffAC);
        remote.pressButton();
        
        // Undo the operation
        remote.pressUndo();  // Should turn on the AC
    }
}
