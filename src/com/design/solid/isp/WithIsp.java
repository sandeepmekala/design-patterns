package com.design.solid.isp;

// Example applying ISP

interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Manager implements Workable {
    @Override
    public void work() {
        // Manager-specific work
    }
}

class Programmer implements Workable, Eatable {
    @Override
    public void work() {
        // Programmer-specific work
    }

    @Override
    public void eat() {
        // Programmer-specific eating behavior
    }
}

public class WithIsp {
    
}
