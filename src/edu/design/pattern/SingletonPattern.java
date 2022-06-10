package edu.design.pattern;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

// It is a Creational Pattern
// Used when we want to eliminate the option of instantiating more then one object
public class SingletonPattern {

	public static void main(String[] args) {
		Singleton instance1 = Singleton.getInstance();
		System.out.println("Instance1 ID: "+System.identityHashCode(instance1));
		System.out.println(instance1.getLetterList());
		System.out.println("Player1 tiles: "+instance1.getTiles(5));
		System.out.println(instance1.getLetterList());
		
		Singleton instance2 = Singleton.getInstance();
		System.out.println("Instance2 ID: "+System.identityHashCode(instance2));
		System.out.println(instance2.getLetterList());
		System.out.println("Player2 tiles: "+instance2.getTiles(5));
		System.out.println(instance1.getLetterList());
	}

}
class Singleton{	
	private LinkedList<String> letters = new LinkedList<String>(Arrays.asList("a","a","a","a","a","a","a","a","b","b","b","b","b","b","b","c","c","c","c","c","d","d","d","d","e","e","e","e","f","f","f","g","g","h","h","h","h"));
	
	public static Singleton instance = null;		// This kind  of objects need to be available on a global basis
	private Singleton(){}
	
	public static Singleton getInstance(){
		synchronized(Singleton.class){				// Make it thread safe
			if(instance == null){
				instance = new Singleton();			// Lazy instantiation
				Collections.shuffle(instance.letters);
			}
		}
		return instance;
	}
	
	public LinkedList<String> getLetterList(){
		return instance.letters;
	}
	public LinkedList<String> getTiles(int howManyTiles){
		LinkedList<String> tilesToReturn = new LinkedList<String>();
		for(int i=0; i<howManyTiles; i++){
			tilesToReturn.add(instance.letters.remove(0));
		}
		return tilesToReturn;
	}
}
