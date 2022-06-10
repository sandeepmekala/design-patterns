package edu.design.pattern;

import java.util.ArrayList;

// It is a Creational Pattern
// The Observer pattern is a software design pattern in which an object called subject, maintains a list of its dependents, called observers, and notify them automatically of any state changes, usually by calling one of their methods. 
// Used when we need to update many other objects when another object changes
// Publisher and Subscriber pattern 
public class ObserverPattern {

	public static void main(String[] args) {
		StockGrabber stockGrabber = new StockGrabber();
		Observer observer1 = new StockObserver(stockGrabber);
		
		stockGrabber.setIbmPrice(123.00);
		stockGrabber.setAaplPrice(432.09);
		stockGrabber.setGoogPrice(233.76);
		
		Observer observer2 = new StockObserver(stockGrabber);
		
		stockGrabber.setIbmPrice(223.00);
		stockGrabber.setAaplPrice(532.09);
		stockGrabber.setGoogPrice(333.76);
		
		stockGrabber.unregister(observer1);
		stockGrabber.setIbmPrice(323.00);
	}

}

interface Subject{
	public void register(Observer o);
	public void unregister(Observer o);
	public void notifyObservers();
}
class StockGrabber implements Subject{

	ArrayList<Observer> observers;
	private double ibmPrice;
	private double aaplPrice;
	private double googPrice;
	public StockGrabber(){
		observers = new ArrayList<Observer>();
	}
	public void register(Observer o) {
		observers.add(o);
	}

	public void unregister(Observer o) {
		int index = observers.indexOf(o);
		System.out.println("Observer "+(index+1)+" deleted");
		observers.remove(index);
	}

	public void notifyObservers() {
		for(Observer observer: observers){
			observer.update(ibmPrice, aaplPrice, googPrice);
		}
	}
	public void setIbmPrice(double ibmPrice) {
		this.ibmPrice = ibmPrice;
		this.notifyObservers();
	}
	public void setAaplPrice(double aaplPrice) {
		this.aaplPrice = aaplPrice;
		this.notifyObservers();
	}
	public void setGoogPrice(double googPrice) {
		this.googPrice = googPrice;
		this.notifyObservers();
	}
	
}
interface Observer{
	public void update(double ibmPrice, double aaplPrice, double googPrice);
}
class StockObserver implements Observer{
	private double ibmPrice;
	private double aaplPrice;
	private double googPrice;
	
	private static int observerIdTracker = 0;
	private int observerId;
	private Subject stockGrabber;
	public StockObserver(Subject stockGrabber){
		this.observerId = ++observerIdTracker;
		this.stockGrabber = stockGrabber;
		System.out.println("New Observer "+observerId);
		this.stockGrabber.register(this);
	}
	public void update(double ibmPrice, double aaplPrice, double googPrice) {
		this.ibmPrice = ibmPrice;
		this.aaplPrice = aaplPrice;
		this.googPrice = googPrice;
		
		printThePrices();
	}
	private void printThePrices() {
		System.out.println(observerId+"\nIBM: "+ibmPrice+"\nAAPL: "+aaplPrice+"\nGOOG: "+googPrice+"\n");
	}
	
}

