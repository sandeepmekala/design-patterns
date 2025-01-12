package com.design.pattern.behavioural;

import java.util.ArrayList;
import java.util.List;

// Memento Class
class Memento {
    private final String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

// Originator Class
class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento createMemento() {
        return new Memento(state);
    }

    public void restoreMemento(Memento memento) {
        this.state = memento.getState();
    }
}

// Caretaker Class
class Caretaker {
    private final List<Memento> mementoList = new ArrayList<>();

    public void addMemento(Memento memento) {
        mementoList.add(memento);
    }

    public Memento undo() {
        if (!mementoList.isEmpty()) {
            return mementoList.remove(mementoList.size() - 1);
        }
        return null;
    }
}

// Client Code
public class MementoClinet {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        // Initial State
        originator.setState("State1");
        caretaker.addMemento(originator.createMemento());

        // Change State
        originator.setState("State2");
        caretaker.addMemento(originator.createMemento());

        // Change Again
        originator.setState("State3");
        System.out.println("Current State: " + originator.getState());

        // Undo
        originator.restoreMemento(caretaker.undo());
        System.out.println("After Undo: " + originator.getState());

        // Undo Again
        originator.restoreMemento(caretaker.undo());
        System.out.println("After Second Undo: " + originator.getState());
    }
}

