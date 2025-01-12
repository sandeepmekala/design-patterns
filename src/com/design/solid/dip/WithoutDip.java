package com.design.solid.dip;

// Example without applying DIP

class LightBulb {
    public void turnOn() {
        System.out.println("LightBulb: Bulb turned on");
    }

    public void turnOff() {
        System.out.println("LightBulb: Bulb turned off");
    }
}

class Switch {
    private LightBulb lightBulb;

    public Switch() {
        this.lightBulb = new LightBulb();
    }

    public void toggle() {
        if (lightBulb != null) {
            if (lightBulb.isOn()) {
                lightBulb.turnOff();
            } else {
                lightBulb.turnOn();
            }
        }
    }
}

public class WithoutDip {
    
}
