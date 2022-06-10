package edu.design.pattern;

// It is a Creational Pattern
// It is like a factory pattern, but everything is encapsulated, the methods that create the object, the factoried that create the objects, the final objects. 
// The final objects contains objects that use strategy pattern
public class AbstractFactoryPattern2 {
	public static void main(String args[]) {
		EnemyShipBuilding2 makeUFOs = new UFOEnemyShipBuilding2();
		EnemyShip2 theGrunt = makeUFOs.orderTheShip("UFO");
		doEnemyStuff(theGrunt);
		System.out.println(theGrunt);
		EnemyShip2 theBoss = makeUFOs.orderTheShip("UFO BOSS");
		doEnemyStuff(theBoss);
		System.out.println(theBoss);
	}
	private static void doEnemyStuff(EnemyShip2 theEnemyShip){
		
		theEnemyShip.displayEnemyShip();
		theEnemyShip.followHeroShip();
		theEnemyShip.enemyShipShoots();
	}
}
abstract class EnemyShipBuilding2{
	protected abstract EnemyShip2 makeEnemyShip(String typeOfShip);
	
	public EnemyShip2 orderTheShip(String typeOfShip) {
		EnemyShip2 theEnemyShip = makeEnemyShip(typeOfShip);
		theEnemyShip.makeShip();
		return theEnemyShip;
	}
}
class UFOEnemyShipBuilding2 extends EnemyShipBuilding2{

	protected EnemyShip2 makeEnemyShip(String typeOfShip) {
		EnemyShip2 theEnemyShip = null;
		
		if("UFO".equals(typeOfShip)){
			EnemyShipFactory2 shipPartsFactory = new UFOEnemyShipFactory2();
			theEnemyShip = new UFOEnemyShip2(shipPartsFactory);
			theEnemyShip.setName("UFO Grunt Ship");
		}else if("UFO BOSS".equals(typeOfShip)){
			EnemyShipFactory2 shipPartsFactory = new UFOBossEnemyShipFactory();
			theEnemyShip = new UFOBossEnemyShip2(shipPartsFactory);
			theEnemyShip.setName("UFO Boss Ship");
		}
		return theEnemyShip;
	}
}

interface EnemyShipFactory2{
	public ESWeapon addESGun();
	public ESEngine addESEngine();
}
class UFOEnemyShipFactory2 implements EnemyShipFactory2{

	public ESWeapon addESGun() {
		return new ESUFOGun();
	}

	public ESEngine addESEngine() {
		return new ESUFOEngine();
	}
}
class UFOBossEnemyShipFactory implements EnemyShipFactory2{

	public ESWeapon addESGun() {
		return null;
	}

	public ESEngine addESEngine() {
		return null;
	}
	
}
abstract class EnemyShip2{
	private String name;
	ESWeapon weapon;
	ESEngine engine;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	abstract void makeShip();
	
	public void followHeroShip(){
		System.out.println(getName()+" is following hero ship");
	}
	public void displayEnemyShip(){
		System.out.println(getName()+" is displayed on the screen");
	}
	public void enemyShipShoots(){
		System.out.println(getName()+" attachs");
	}
	
	public String toString(){
		return "The "+name+" has a top speed of "+engine+" and an attack power of "+weapon;
	}
}
class UFOEnemyShip2 extends EnemyShip2{
	EnemyShipFactory2 shipFactory;
	public UFOEnemyShip2(EnemyShipFactory2 shipPartsFactory) {
		shipFactory = shipPartsFactory;
	}
	void makeShip() {
		System.out.println("Making enemy ship "+getName());
		weapon = shipFactory.addESGun();
		engine = shipFactory.addESEngine();
	}
}
class UFOBossEnemyShip2 extends EnemyShip2{
	EnemyShipFactory2 shipFactory;
	public UFOBossEnemyShip2(EnemyShipFactory2 shipPartsFactory) {
		shipFactory = shipPartsFactory;
	}
	void makeShip() {
		System.out.println("Making enemy boss ship "+getName());
		weapon = shipFactory.addESGun();
		engine = shipFactory.addESEngine();
	}
}
interface ESWeapon{
	public String toString();
}
interface ESEngine{
	public String toString();
}
class ESUFOGun implements ESWeapon{
	public String toString(){
		return "20 damage";
	}
}
class ESUFOEngine implements ESEngine{
	public String toString(){
		return "1000 mph";
	}
}
