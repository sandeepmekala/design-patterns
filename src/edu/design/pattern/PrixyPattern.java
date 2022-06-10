package edu.design.pattern;

// It is a Structural pattern
// Provides a class with will limit access to another class
// You may do this for security reasons
// In this example, ATMProxy doesn't give us access to setter methods
public class PrixyPattern {
	public static void main(String[] args) {
		GetATMData atmProxy = new ATMProxy();
		System.out.println("ATM state: "+atmProxy.getATMState());
		System.out.println("Cash in ATM: "+atmProxy.getCashInMachine());
		//atmProxy.set
	}
}

class ATMState {

}
interface GetATMData{
	public ATMState getATMState();
	public int getCashInMachine();
}
class ATMMachine implements GetATMData{
	private ATMState atmState;
	private int cashInMachine = 2000;
	public ATMState getATMState() {
		return atmState;
	}
	public void setATMState(ATMState atmState) {
		this.atmState = atmState;
	}
	public int getCashInMachine() {
		return cashInMachine;
	}
	public void setCashInMachine(int cashInMachine) {
		this.cashInMachine = cashInMachine;
	}
}
class ATMProxy implements GetATMData{
	ATMMachine realATMMachine = new ATMMachine();
	public ATMState getATMState() {
		return realATMMachine.getATMState();
	}
	public int getCashInMachine() {
		return realATMMachine.getCashInMachine();
	}
	
}
