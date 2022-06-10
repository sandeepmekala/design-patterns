package edu.design.pattern;

// It is a Behavioural pattern
// Allows two incompatible interfaces to works together
// 
public class AdapterPattern {

	public static void main(String[] args) {
		EnemyAttacker tank = new EmneyTank();
		EnemyRobot robot = new EnemyRobot();
		
		System.out.println("The Tank: ");
		tank.assignDriver("Paul");
		tank.driveForword();
		tank.fireWeapons();
		
		System.out.println("The Robot :");
		robot.reactToHuman("Frank");
		robot.walkForward();
		robot.smashWithHands();
		
		EnemyAttacker newRobot = new EnemyRobotAdapter(robot);
		newRobot.assignDriver("Gimmy");
		newRobot.driveForword();
		newRobot.fireWeapons();
	}
}
interface EnemyAttacker{
	public void fireWeapons();
	public void driveForword();
	public void assignDriver(String driverName);
}
class EmneyTank implements EnemyAttacker{

	public void fireWeapons() {
		System.out.println("Attackes and does damange");
	}

	@Override
	public void driveForword() {
		System.out.println("drive forward");
	}

	@Override
	public void assignDriver(String driverName) {
		System.out.println(driverName+" is driving the tank");
	}
}
class EnemyRobot{
	public void smashWithHands(){
		System.out.println("Does damager with hands");
	}
	public void walkForward(){
		System.out.println("Robot is waling forward");
	}
	public void reactToHuman(String driverName){
		System.out.println("Kills the "+driverName);
	}
}
class EnemyRobotAdapter implements EnemyAttacker{
	EnemyRobot enemyRobot;
	public EnemyRobotAdapter(EnemyRobot enemyRobot){
		this.enemyRobot = enemyRobot;
	}
	public void fireWeapons() {
		this.enemyRobot.smashWithHands();
	}
	public void driveForword() {
		this.enemyRobot.walkForward();
	}
	public void assignDriver(String driverName) {
		this.enemyRobot.reactToHuman(driverName);
	}
	
}
