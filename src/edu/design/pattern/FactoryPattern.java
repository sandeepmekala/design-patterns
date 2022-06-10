package edu.design.pattern;

// Creational Pattern
// The Factory Pattern allows you to create objects without specifying the exact class of object that will be created 
// When a method returns one of several possible classes that share a common super class
public class FactoryPattern {

	public static void main(String[] args) {
		EnemyShip enemyShip = null;
		EnemyShipFactory enemyShipFactory = new EnemyShipFactory();
		String shipType = "R";
		enemyShip = enemyShipFactory.getEnemyShip(shipType);
		if(null != enemyShip)doEnemyStuff(enemyShip);
	}

	private static void doEnemyStuff(EnemyShip enemyShip) {
		enemyShip.displayEnemyShip();
		enemyShip.followHeroShip();
		enemyShip.enemyShipShoots();		
	}	
}

abstract class EnemyShip{
	private String shipName;
	private double amtDamage;
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public double getAmtDamage() {
		return amtDamage;
	}
	public void setAmtDamage(double amtDamage) {
		this.amtDamage = amtDamage;
	}
	public void followHeroShip(){
		System.out.println(getShipName()+" is following hero ship");
	}
	public void displayEnemyShip(){
		System.out.println(getShipName()+" is displayed on the screen");
	}
	public void enemyShipShoots(){
		System.out.println(getShipName()+" attachs and does "+getAmtDamage());
	}
}
class UFOEnemyShip extends EnemyShip{
	public UFOEnemyShip(){
		setShipName("UFO Enemy ship");
		setAmtDamage(20.0);
	}
}
class RocketEnemyShip extends EnemyShip{
	public RocketEnemyShip(){
		setShipName("Rocket Enemy ship");
		setAmtDamage(10.0);
	}
}
class EnemyShipFactory{
	public EnemyShip getEnemyShip(String shipType){
		EnemyShip enemyShip = null;
		if("U".equals(shipType)){
			enemyShip = new UFOEnemyShip();
		}else if("R".equals(shipType)){
			enemyShip = new RocketEnemyShip();
		}
		return enemyShip;
	}
}