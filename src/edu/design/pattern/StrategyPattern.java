package edu.design.pattern;

// It is a Creational Pattern
// Defines a family of algorithms, encapsulate each one, and make them interchangeable. The strategy pattern lets the algorithm vary independently from client that use it.
// Needed when you need to use one of the several behaviors dynamically
// Reduces code duplication
// Keeps class changes from forcing other class changes
public class StrategyPattern {

	public static void main(String[] args) {
		Animal dog = new Dog();
		Animal bird = new Bird();
		System.out.println("Dog: "+dog.tryToFly());
		System.out.println("Bird: "+bird.tryToFly());
		
		//make them interchangeable
		dog.setFlyingType(new CanFly());
		System.out.println("Dog: "+dog.tryToFly());
	}
}
class Animal{
	private String name;
	
	protected Fly flyingType;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String tryToFly(){
		return flyingType.fly();
	}
	//encapsulate each one
	public void setFlyingType(Fly flyingType) {
		this.flyingType = flyingType;
	}
}
class Dog extends Animal{
	public Dog(){
		super();
		flyingType = new CantFly();
	}
	public void digHole(){
		System.out.println("Digging hole");
	}
}
class Bird extends Animal{
	public Bird(){
		super();
		flyingType = new CanFly();
	}
}

//Defines a family of algorithms
interface Fly{
	public String fly();
}
class CanFly implements Fly{
	public String fly() {
		return "Can fly";
	}
}
class CantFly implements Fly{
	public String fly() {
		return "Can't fly";
	}
}
	
