package com.design.solid.isp;

// Example without applying ISP

interface Worker {
    void work();
    void eat();
}

class Manager implements Worker {
    @Override
    public void work() {
        // Manager-specific work
    }

    @Override
    public void eat() {
        // Manager-specific eating behavior
    }
}

class Programmer implements Worker {
    @Override
    public void work() {
        // Programmer-specific work
    }

    @Override
    public void eat() {
        // Programmer-specific eating behavior
    }
}

public class WithoutIsp {
    
}
