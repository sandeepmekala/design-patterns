package com.design.pattern.creational;

// Creational Pattern
// The Factory Pattern allows you to create objects without specifying the exact class of object that will be created 
// When a method returns one of several possible classes that share a common super class
public class FactoryMethodPattern {

	public static void main(String[] args) {
		EnemyShipFactory enemyShipFactory = new EnemyShipFactory();

		EnemyShip enemyShip = enemyShipFactory.getEnemyShip("R");
		System.out.println(enemyShip.getShipName());
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

abstract class EnemyShip{
	private String shipName;
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
}
class UFOEnemyShip extends EnemyShip{
	public UFOEnemyShip(){
		setShipName("UFO Enemy ship");
	}
}
class RocketEnemyShip extends EnemyShip{
	public RocketEnemyShip(){
		setShipName("Rocket Enemy ship");
	}
}