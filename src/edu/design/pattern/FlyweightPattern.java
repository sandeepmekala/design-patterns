package edu.design.pattern;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Random;
 
// Used when you want to create large number similar objects in Hundreds of thousands
// To reduce memory usage you share the object that are similar in some way rather then creating new ones
// Intrinsic state and Extrinsic state
public class FlyweightPattern {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		long startMem = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
		// Normal Approach
		// Creates lot of objects
		// Time taken: 528
		// Mem taken: 122395
		for(int i=0; i< 2000000; i++){
			Rect rect = new Rect(getRandomColor(),getRandomX(),getRandomY(),getRandomX(),getRandomY());
			rect.draw();
		}
		
		// Flyweight Pattern Approach
		// Try to reuse the objects with same state
		// Time taken: 1016
		// Mem taken: 64209
		RectFactory rectFactory = new RectFactory();
		for(int i=0; i< 2000000; i++){
			Rect rect = rectFactory.getRect(getRandomColor());
			rect.draw(getRandomX(),getRandomY(),getRandomX(),getRandomY());
		}
		
		long endTime = System.currentTimeMillis();
		long endMem = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
		System.out.println("Time taken: "+(endTime-startTime));
		System.out.println("Mem taken: "+(endMem-startMem)/1024);
	}
	public static int getRandomColor(){
		Random random = new Random();
		return random.nextInt(9);
	}
	public static int getRandomX(){
		Random random = new Random();
		return random.nextInt(1000);
	}
	public static int getRandomY(){
		Random random = new Random();
		return random.nextInt(500);
	}
}
class Rect{
	private int color;
	private int x;
	private int y;
	private int x2;
	private int y2;
	
	//Normal Approach
	public Rect(int color, int x, int y, int x2, int y2) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}
	public void draw(){
		//System.out.println("drawing: "+x+", "+y+", "+x2+", "+y2);
	}
	
	//Flyweight Pattern Approach
	public Rect(int color) {
		this.color = color;
	}
	public void draw(int x, int y, int x2, int y2){
		//System.out.println("drawing"+color+": "+x+", "+y+", "+x2+", "+y2);
	}
}
class RectFactory{
	HashMap<Integer, Rect> map = new HashMap<Integer, Rect>();
	public Rect getRect(int color){
		Rect rect = map.get(color);
		if(null == rect){
			rect = new Rect(color);
			map.put(color, rect);
		} 
		return rect;
	}
}
