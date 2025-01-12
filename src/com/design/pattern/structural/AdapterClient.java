package com.design.pattern.structural;

// Step 1: Adaptee (Existing Interface)
interface WeightMachine {
    double getWeightInPounds();
}

class WeightMachineForBabies implements WeightMachine {
    @Override
    public double getWeightInPounds() {
        return 28.0; // Returns weight in pounds
    }
}

// Step 2: Target Interface (Expected Interface)
interface WeightMachineAdapter {
    double getWeightInKilograms();
}

// Step 3: Adapter Implementation
class WeightMachineAdapterImpl implements WeightMachineAdapter {
    private WeightMachine weightMachine;

    public WeightMachineAdapterImpl(WeightMachine weightMachine) {
        this.weightMachine = weightMachine;
    }

    @Override
    public double getWeightInKilograms() {
        double weightInPounds = weightMachine.getWeightInPounds();
        return convertPoundsToKilograms(weightInPounds);
    }

    private double convertPoundsToKilograms(double pounds) {
        return pounds * 0.453592; // Conversion logic
    }
}

// Step 4: Client
public class AdapterClient {
    public static void main(String[] args) {
        WeightMachine weightMachine = new WeightMachineForBabies();
        WeightMachineAdapter adapter = new WeightMachineAdapterImpl(weightMachine);

        System.out.println("Weight in Kilograms: " + adapter.getWeightInKilograms());
    }
}