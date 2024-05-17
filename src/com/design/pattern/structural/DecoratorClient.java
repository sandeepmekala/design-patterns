package com.design.pattern.structural;

// It is a Behavioral Pattern
// Allows you to modify an object dynamically
// You would use it when you want the capabilities of inheritance but want to add the functionality dynamically
// It is more flexible then inheritance
// Component
interface Coffee {
    String getDescription();
    double cost();
}

// ConcreteComponent
class BasicCoffee implements Coffee {
    public String getDescription() {
        return "Basic Coffee";
    }

    public double cost() {
        return 2.0;
    }
}

// Decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    public double cost() {
        return decoratedCoffee.cost();
    }
}

// ConcreteDecorator
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    public String getDescription() {
        return super.getDescription() + ", with Milk";
    }

    public double cost() {
        return super.cost() + 0.5; // Milk costs extra
    }
}

// ConcreteDecorator
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    public String getDescription() {
        return super.getDescription() + ", with Sugar";
    }
	
}

// Usage
public class DecoratorClient {
    public static void main(String[] args) {
        // Creating a basic coffee
        Coffee basicCoffee = new BasicCoffee();

        // Wrapping it with decorators
        Coffee milkCoffee = new MilkDecorator(basicCoffee);
        Coffee milkAndSugarCoffee = new SugarDecorator(milkCoffee);

        // Getting description and cost
        System.out.println("Description: " + milkAndSugarCoffee.getDescription());
        System.out.println("Cost: $" + milkAndSugarCoffee.cost());
    }
}
