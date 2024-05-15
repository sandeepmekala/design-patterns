package com.design.pattern.creational;

// It is a Creational Pattern
// Used when we want to eliminate the option of instantiating more then one object
// Logger classes
public class SingletonPattern {

	public static void main(String[] args) {
		Singleton instance1 = Singleton.getInstance();
		System.out.println("Instance1 ID: "+System.identityHashCode(instance1));
		
		Singleton instance2 = Singleton.getInstance();
		System.out.println("Instance2 ID: "+System.identityHashCode(instance2));
	}

}

class Singleton{	
	private static Singleton instance = null;		// This kind  of objects need to be available on a global basis
	private Singleton(){}
	
	public static Singleton getInstance(){
		synchronized(Singleton.class){				// Make it thread safe
			if(instance == null){
				instance = new Singleton();			// Lazy instantiation
			}
		}
		return instance;
	}
}
