package com.design.pattern.creational;

// It is a Creational Pattern
// Used when we need to create new object by cloning(copying) other object
public class PrototypePattern {

	public static void main(String[] args) {
		CloneFactory cloneFactory = new CloneFactory();
		Sheep shally = new Sheep();
		Sheep cloneSheep = (Sheep) cloneFactory.getClone(shally);
		System.out.println("Shally Hashcode: "+System.identityHashCode(shally));
		System.out.println("Clone Hashcode: "+System.identityHashCode(cloneSheep));
	}
}

class CloneFactory{
	public Animal getClone(Animal sampleSheep){
		return sampleSheep.copy();
	}
}

interface Animal extends Cloneable{
	public Animal copy();
}
class Sheep implements Animal{
	public Sheep(){
		System.out.println("Sheep it made");
	}
	public Animal copy() {
		System.out.println("Sheep is being made");
		Sheep newSheep = null;
		try {
			newSheep = (Sheep) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return newSheep;
	}
}
