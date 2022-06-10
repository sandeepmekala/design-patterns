package edu.design.pattern;
 
// Decouple abstraction from its implementation so that two can vary independently
// 2 layers of abstraction between classes and 1 class is dependent on other
// Can be used when you want to be able to change both the abstraction(abstract classes) and concrete classes independently
public class BridgePattern {
	public static void main(String[] args) {
		RemoteButton tv1 = new TvRemoteMute(new TVDevice(1, 200));
		RemoteButton tv2 = new TvRemotePause(new TVDevice(1, 200));
		RemoteButton dvd1 = new DVDRemote(new DVDDevice(1, 200));
		
		System.out.println("Test TV with Mute:");
		tv1.buttonFivePressed();
		tv1.buttonSixPressed();
		tv1.buttonNinePressed();
		
		System.out.println("Test TV with Pause:");
		tv2.buttonFivePressed();
		tv2.buttonSixPressed();
		tv2.buttonSixPressed();
		tv2.buttonSixPressed();
		tv2.buttonNinePressed();
		tv2.deviceFeedback();
		
		System.out.println("Test DVD:");
		dvd1.buttonFivePressed();
		dvd1.buttonSixPressed();
		dvd1.buttonNinePressed();
	}
}
abstract class EntertainmentDevice{
	protected int deviceState;
	protected int maxSettings;
	private int volumeLevel = 0;
	
	public abstract void buttonFivePressed();
	public abstract void buttonSixPressed();
	
	public void deviceFeedback(){
		if(deviceState > maxSettings || deviceState < 0){
			deviceState = 0;
		}
		System.out.println("On :"+deviceState);
	}
	public void buttonSevenPressed(){
		volumeLevel++;
		System.out.println("VolumeLevel at: "+volumeLevel);
	}
	public void buttonEightPressed(){
		volumeLevel--;
		System.out.println("VolumeLevel at: "+volumeLevel);
	}
}
class TVDevice extends EntertainmentDevice{
	public TVDevice(int deviceState, int maxSettings){
		this.deviceState = deviceState;
		this.maxSettings = maxSettings;
	}
	public void buttonFivePressed() {
		deviceState--;
		System.out.println("Channel Down");
	}
	public void buttonSixPressed() {
		deviceState++;
		System.out.println("Channel Up");
	}
}
class DVDDevice extends EntertainmentDevice{
	public DVDDevice(int deviceState, int maxSettings){
		this.deviceState = deviceState;
		this.maxSettings = maxSettings;
	}
	public void buttonFivePressed() {
		deviceState--;
		System.out.println("Prvious Channel");
	}
	public void buttonSixPressed() {
		deviceState++;
		System.out.println("Next Channel");
	}
}
abstract class RemoteButton{
	private EntertainmentDevice device;
	public RemoteButton(EntertainmentDevice device){
		this.device = device;
	}
	public void buttonFivePressed(){
		device.buttonFivePressed();
	}
	public void buttonSixPressed(){
		device.buttonSixPressed();
	}
	public void deviceFeedback(){
		device.deviceFeedback();
	}
	public abstract void buttonNinePressed();
}
class TvRemoteMute extends RemoteButton{
	public TvRemoteMute(EntertainmentDevice device) {
		super(device);
	}
	public void buttonNinePressed() {
		System.out.println("TV was on Mute");
	}
}
class TvRemotePause extends RemoteButton{
	public TvRemotePause(EntertainmentDevice device) {
		super(device);
	}
	public void buttonNinePressed() {
		System.out.println("TV was on Pause");
	}
}
class DVDRemote extends RemoteButton{
	public DVDRemote(EntertainmentDevice device) {
		super(device);
	}
	public void buttonNinePressed() {
		System.out.println("DVD was on Pause");
	}
}
