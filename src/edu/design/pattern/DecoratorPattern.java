package edu.design.pattern;

// It is a Behavioral Pattern
// Allows you to modify an object dynamically
// You would use it when you want the capabilities of inheritance but want to add the functionality dynamically
// It is more flexible then inheritance
public class DecoratorPattern {

	public static void main(String[] args) {
		Pizza pizza = new TomatoSauce(new Mozzarella(new PlainPizza()));
		System.out.println("description: "+pizza.getDescription());
		System.out.println("cost: "+ pizza.getCost());
	}
}
interface Pizza{
	public String getDescription();
	public double getCost();
}
class PlainPizza implements Pizza{
	public PlainPizza(){
		System.out.println("Adding Dough");
	}
	public String getDescription() {
		return "This Dough";
	}

	public double getCost() {
		return 4.00;
	}
}
abstract class ToppingDecorator implements Pizza{
	Pizza tempPizza;
	public ToppingDecorator(Pizza newPizza){
		tempPizza = newPizza;
	}
	public String getDescription() {
		return tempPizza.getDescription();
	}

	public double getCost() {
		return tempPizza.getCost();
	}
}
class Mozzarella extends ToppingDecorator{

	public Mozzarella(Pizza newPizza) {
		super(newPizza);		
		System.out.println("Adding Mozz");
	}
	public String getDescription() {
		return tempPizza.getDescription()+", Mozzorella";
	}

	public double getCost() {
		return tempPizza.getCost()+ 0.50;
	}
}
class TomatoSauce extends ToppingDecorator{

	public TomatoSauce(Pizza newPizza) {
		super(newPizza);
		System.out.println("Adding Sauce");
	}
	public String getDescription() {
		return tempPizza.getDescription()+", Sauce";
	}

	public double getCost() {
		return tempPizza.getCost()+ 0.25;
	}
}
