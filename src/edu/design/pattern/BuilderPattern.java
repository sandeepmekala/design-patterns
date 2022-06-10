package edu.design.pattern;

// It is a Creational Pattern
// Used when we want to build an object made up from other objects
// When we want to keep the creation of other object separate from main object
public class BuilderPattern {

	public static void main(String[] args) {
		RobotBuilder oldStylerobotBuilder = new OldRobotBuilder();
		RobotEngineer robotEngineer = new RobotEngineer(oldStylerobotBuilder);
		robotEngineer.makeRobot();
		Robot robot = robotEngineer.getRobot();
		System.out.println(robot);
	}
}
interface RobotPlan{
	public void setRobotHead(String robotHead);
	public void setRobotTorso(String robotTorso);
	public void setRobotArms(String robotArms);
	public void setRobotLegs(String robotLegs);
}
class Robot implements RobotPlan{
	private String robotHead;
	private String robotTorso;
	private String robotArms;
	private String robotLegs;
	public String getRobotHead() {
		return robotHead;
	}
	public void setRobotHead(String robotHead) {
		this.robotHead = robotHead;
	}
	public String getRobotTorso() {
		return robotTorso;
	}
	public void setRobotTorso(String robotTorso) {
		this.robotTorso = robotTorso;
	}
	public String getRobotArms() {
		return robotArms;
	}
	public void setRobotArms(String robotArms) {
		this.robotArms = robotArms;
	}
	public String getRobotLegs() {
		return robotLegs;
	}
	public void setRobotLegs(String robotLegs) {
		this.robotLegs = robotLegs;
	}
	@Override
	public String toString() {
		return "Robot [robotHead=" + robotHead + ", robotTorso=" + robotTorso + ", robotArms=" + robotArms
				+ ", robotLegs=" + robotLegs + "]";
	}
}
interface RobotBuilder{
	public void buildRobotHead();
	public void buildRobotTorso();
	public void buildRobotArms();
	public void buildRobotLegs();
	public void buildRobot();
	public Robot getRobot();
}
class OldRobotBuilder implements RobotBuilder{
	Robot robot = null;
	public OldRobotBuilder(){
		robot = new Robot();
	}
	public void buildRobotHead() {
		robot.setRobotHead("Tin Head");
	}
	public void buildRobotTorso() {
		robot.setRobotTorso("Tin Torso");
	}
	public void buildRobotArms() {
		robot.setRobotArms("Long Armgs");
	}
	public void buildRobotLegs() {
		robot.setRobotLegs("Roller Skates");
	}
	public void buildRobot() {
		buildRobotHead();
		buildRobotTorso();
		buildRobotArms();
		buildRobotLegs();
	}
	public Robot getRobot() {
		return robot;
	}
}
class RobotEngineer{
	private RobotBuilder robotBuilder;
	public RobotEngineer(RobotBuilder robotBuilder){
		this.robotBuilder = robotBuilder;
	}
	public void makeRobot() {
		this.robotBuilder.buildRobot();
	}
	public Robot getRobot(){
		return this.robotBuilder.getRobot();
	}
}
