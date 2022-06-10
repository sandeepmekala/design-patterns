package edu.design.pattern;

// It is a Creational Pattern
// Used when we need to create new object by cloning(copying) other object
public class PrototypePattern {

	public static void main(String[] args) {
		CloneFactory cloneFactory = new CloneFactory();
		Sheep shally = new Sheep();
		Sheep cloneSheep = (Sheep) cloneFactory.getClone(shally);
		System.out.println(shally);
		System.out.println(cloneSheep);
		System.out.println("Shally Hashcode: "+System.identityHashCode(shally));
		System.out.println("Clone Hashcode: "+System.identityHashCode(cloneSheep));
	}
}
interface Animal2 extends Cloneable{
	public Animal2 makeCopy();
}
class Sheep implements Animal2{
	public Sheep(){
		System.out.println("Sheep it made");
	}
	public Animal2 makeCopy() {
		System.out.println("Sheep is being made");
		Sheep newSheep = null;
		try {
			newSheep = (Sheep) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return newSheep;
	}
	public String toString() {
		return "Sheep is Dally";
	}	
}
class CloneFactory{
	public Animal2 getClone(Animal2 sampleSheep){
		return sampleSheep.makeCopy();
	}
}
