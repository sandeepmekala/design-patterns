package edu.design.pattern;
 
// Used to create a group of subclassses that have to execute similar group of methods
// You can create an abstract class that contains a method called the template
// The template method will have series of method calls that every subclass object call
// The subclass objects can override some of the method calls
public class TemplatePattern {

	public static void main(String[] args) {
		Hoagie cust12Hoagie = new ItalianHoagie();
		cust12Hoagie.makeSandwich();
		System.out.println();
		Hoagie cust13Hoagie = new VegieHoagie();
		cust13Hoagie.makeSandwich();
	}
}
abstract class Hoagie{
	final void makeSandwich(){
		cutBun();
		if(wantMeat()){
			addMeat();
		}
		if(wantCheese()){
			addCheese();
		}
		if(wantVegies()){
			addVegies();
		}
		if(wantCondiments()){
			addCondiments();
		}
		wrapSandwich();
	}
	private void cutBun() {
		System.out.println("The Hoagie is cut");
	}
	public abstract void addCondiments();
	public abstract void addVegies();
	public abstract void addCheese();
	public abstract void addMeat();
	private void wrapSandwich() {
		System.out.println("Wrap the Hoagie");
	}	
	public boolean wantMeat() {
		return true;
	}
	private boolean wantCondiments() {
		return true;
	}
	private boolean wantVegies() {
		return true;
	}
	private boolean wantCheese() {
		return true;
	}
}
class ItalianHoagie extends Hoagie{
	public void addCondiments() {
		System.out.println("Adding the Italian Condiments");
	}
	public void addVegies() {
		System.out.println("Adding the Italian Vegies");
	}
	public void addCheese() {
		System.out.println("Adding the Italian Cheese");
	}
	public void addMeat() {
		System.out.println("Adding the Italian Meat");
	}
}
class VegieHoagie extends Hoagie{
	public boolean wantMeat(){
		return false;
	}
	public boolean wantCondiments(){
		return false;
	}
	public void addCondiments() {}
	public void addVegies() {
		System.out.println("Adding the Italian Vegies");
	}
	public void addCheese() {
		System.out.println("Adding the Italian Cheese");
	}
	public void addMeat() { }
}
