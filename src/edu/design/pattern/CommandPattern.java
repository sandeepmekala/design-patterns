package edu.design.pattern;

import java.util.ArrayList;

// It is a Behavioral pattern
// In this an object is used to represent and encapsulate all the information needed to call a method at later time
// This information includes method name, object that owns this method and and parameter values
public class CommandPattern {

	public static void main(String[] args) {
		Device television = TvRemote.getDevice();
		Command command = new TurnTvOn(television);
		DeviceButton deviceButton = new DeviceButton(command);
		deviceButton.press();
		
		command = new TurnTvOff(television);
		deviceButton = new DeviceButton(command);
		deviceButton.press();
		
		command = new TurnTvUp(television);
		deviceButton = new DeviceButton(command);
		deviceButton.press();
		deviceButton.press();
		deviceButton.press();
		
		Device radio = new Radio();
		ArrayList<Device> devices = new ArrayList<Device>();
		devices.add(television);
		devices.add(radio);
		
		Command allOff = new TurnItAllOff(devices);
		allOff.excute();
		allOff.undo();
		
	}
}
interface Device{
	public void on();
	public void off();
	public void valumeUp();
	public void valumeDown();
}
//Reciever
class Television implements Device{
	private int volume = 0;
	public void on() {
		System.out.println("TV is ON");
	}
	public void off() {
		System.out.println("TV is OFF");
	}
	public void valumeUp() {
		volume++;
		System.out.println("TV is volumen at "+volume);
	}
	public void valumeDown() {
		volume--;
		System.out.println("TV is volumen at "+volume);
	}
}
class Radio implements Device{
	private int volume = 0;
	public void on() {
		System.out.println("Radio is ON");
	}
	public void off() {
		System.out.println("Radio is OFF");
	}
	public void valumeUp() {
		volume++;
		System.out.println("Radio is volumen at "+volume);
	}
	public void valumeDown() {
		volume--;
		System.out.println("Radio is volumen at "+volume);
	}
}
interface Command{
	public void excute();
	public void undo();
}
class TurnTvOn implements Command{
	Device device;
	public TurnTvOn(Device device){
		this.device = device;
	}
	public void excute() {
		this.device.on();
	}
	public void undo() {
		this.device.off();
	}
}
class TurnTvOff implements Command{
	Device device;
	public TurnTvOff(Device device){
		this.device = device;
	}
	public void excute() {
		this.device.off();
	}
	public void undo() {
		this.device.on();
	}
}
class TurnTvUp implements Command{
	Device device;
	public TurnTvUp(Device device){
		this.device = device;
	}
	public void excute() {
		this.device.valumeUp();
	}
	public void undo() {
		this.device.valumeDown();
	}
}
class TurnItAllOff implements Command{
	ArrayList<Device> devices;
	public TurnItAllOff(ArrayList<Device> devices){
		this.devices = devices;
	}
	public void excute() {
		for(Device device: devices){
			device.off();
		}
	}
	public void undo() {
		for(Device device: devices){
			device.on();
		}
	}
}
//Invoker
class DeviceButton{
	Command command;
	public DeviceButton(Command command){
		this.command = command;
	}
	public void press(){
		this.command.excute();
	}
}
class TvRemote{
	public static Device getDevice(){
		return new Television();
	}
}
