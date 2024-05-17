package com.design.pattern.behavioural;

// It is a Creational Pattern
// Defines a family of algorithms, encapsulate each one, and make them interchangeable. The strategy pattern lets the algorithm vary independently from client that use it.
// Needed when you need to use one of the several behaviors dynamically
// Reduces code duplication
// Keeps class changes from forcing other class changes
// Strategy interface
interface SortingStrategy {
    void sort(int[] array);
}

// Concrete strategies
class BubbleSort implements SortingStrategy {
    public void sort(int[] array) {
        // Bubble sort implementation
    }
}

class QuickSort implements SortingStrategy {
    public void sort(int[] array) {
        // Quick sort implementation
    }
}

// Context
class Sorter {
    private SortingStrategy strategy;

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void performSort(int[] array) {
        strategy.sort(array);
    }
}

// Client
public class StrategyClient {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();

        sorter.setStrategy(new BubbleSort());
        int[] array1 = {5, 2, 7, 1, 3};
        sorter.performSort(array1);

        sorter.setStrategy(new QuickSort());
        int[] array2 = {8, 4, 9, 6, 0};
        sorter.performSort(array2);
    }
}
