package com.design.pattern.structural;

// It is a Behavioural pattern
// Allows two incompatible interfaces to works together
// 
public class AdapterClient {

	public static void main(String[] args) {
		Attacker tank = new Tank();
		tank.drive();

		Robot robot = new Robot();
		robot.walk();
		
		Attacker newRobot = new RobotAdapter(robot);
		newRobot.drive();
	}
}
interface Attacker{
	public int drive();
}
class Tank implements Attacker{
	@Override
	public int drive() {
		System.out.println("drive forward 5kms");
		return 5;
	}
}
class Robot{
	public int walk(){
		System.out.println("Robot is waling forward 2000ms");
		return 2000;
	}
}
class RobotAdapter implements Attacker{
	Robot robot;
	public RobotAdapter(Robot robot){
		this.robot = robot;
	}
	public int drive() {
		return this.robot.walk()/1000;	// As expected behaviour is in kms
	}
}
