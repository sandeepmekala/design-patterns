package com.design.solid.dip;

// Example applying DIP

interface Switchable {
    void turnOn();
    void turnOff();
}

class LightBulb implements Switchable {
    @Override
    public void turnOn() {
        System.out.println("LightBulb: Bulb turned on");
    }

    @Override
    public void turnOff() {
        System.out.println("LightBulb: Bulb turned off");
    }
}

class Switch {
    private Switchable switchable;

    public Switch(Switchable switchable) {
        this.switchable = switchable;
    }

    public void toggle() {
        if (switchable != null) {
            if (switchable.isOn()) {
                switchable.turnOff();
            } else {
                switchable.turnOn();
            }
        }
    }
}

public class WithDip {
    
}
