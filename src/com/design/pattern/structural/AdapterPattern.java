package com.design.pattern;

// It is a Behavioural pattern
// Allows two incompatible interfaces to works together
// 
public class AdapterPattern {

	public static void main(String[] args) {
		EnemyAttacker tank = new EmneyTank();
		tank.driveForword();

		EnemyRobot robot = new EnemyRobot();
		robot.walkForward();
		
		EnemyAttacker newRobot = new EnemyRobotAdapter(robot);
		newRobot.driveForword();
	}
}
interface EnemyAttacker{
	public void driveForword();
}
class EmneyTank implements EnemyAttacker{
	@Override
	public void driveForword() {
		System.out.println("drive forward");
	}
}
class EnemyRobot{
	public void walkForward(){
		System.out.println("Robot is waling forward");
	}
}
class EnemyRobotAdapter implements EnemyAttacker{
	EnemyRobot enemyRobot;
	public EnemyRobotAdapter(EnemyRobot enemyRobot){
		this.enemyRobot = enemyRobot;
	}
	public void driveForword() {
		this.enemyRobot.walkForward();
	}
}
