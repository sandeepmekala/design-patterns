package edu.design.pattern;
 
// Allows a object to alter it behavior when its internal state changes. The object will appear to change its class.
// Context(Account) : Maintains an instance of the concrete state subclass that defines the current state
// State : Defines an interface for encapsulating the behaviors associated with a particular state of the context
// Concrete State : Each subclass implements the behavior associated with the state of context
public class StatePattern {
	public static void main(String[] args) {
		ATMMachine2 atmMachine = new ATMMachine2();
		atmMachine.insertCard();
		atmMachine.ejectCard();
		atmMachine.insertCard();
		atmMachine.insertPin(1234);
		atmMachine.requestCash(2000);
		atmMachine.insertCard();
		atmMachine.insertPin(1234);
	}
}
interface ATMState2{
	void insertCard();
	void ejectCard();
	void insertPin(int pin);
	void requestCash(int cash);
}
class ATMMachine2{
	ATMState2 atmState;
	
	ATMState2 hasCard;
	ATMState2 noCard;
	ATMState2 hasPin;
	ATMState2 noCash;
	
	int cashInATMMachine = 2000;
	boolean correctPinEntered = false;
	public ATMMachine2(){
		hasCard = new HasCard(this);
		noCard = new NoCard(this);
		hasPin = new HasPin(this);
		noCash = new NoCash(this);
		
		atmState = noCard;
		if(cashInATMMachine < 0){
			atmState = noCash;
		}
	}
	public void setAtmState(ATMState2 atmState) {
		this.atmState = atmState;
	}
	public void setCashInATMMachine(int cashInATMMachine) {
		this.cashInATMMachine = cashInATMMachine;
	}
	void insertCard(){
		atmState.insertCard();
	}
	void ejectCard(){
		atmState.ejectCard();
	}
	void insertPin(int pin){
		atmState.insertCard();
	}
	void requestCash(int cash){
		atmState.requestCash(cash);
	}
	public ATMState2 getHasCard() {
		return hasCard;
	}
	public ATMState2 getNoCard() {
		return noCard;
	}
	public ATMState2 getHasPin() {
		return hasPin;
	}
	public ATMState2 getNoCash() {
		return noCash;
	}
}
class HasCard implements ATMState2{
	ATMMachine2 atmMachine;
	public HasCard(ATMMachine2 atmMachine){
		this.atmMachine = atmMachine;
	}
	public void insertCard() {
		System.out.println("You can't insert more then one card");
	}
	public void ejectCard() {
		System.out.println("Card ejected");
		atmMachine.setAtmState(atmMachine.getNoCard());
	}
	public void insertPin(int pin) {
		if(pin == 1234){
			System.out.println("Correct PIN");
			atmMachine.correctPinEntered = true;
			atmMachine.setAtmState(atmMachine.getHasPin());
		}else{
			System.out.println("Incorrect PIN");
			atmMachine.correctPinEntered = false;
			System.out.println("Card ejected");
			atmMachine.setAtmState(atmMachine.getNoCard());
		}
	}
	public void requestCash(int cash) {
		System.out.println("Enter PIN first");
	}
}
class NoCard implements ATMState2{
	ATMMachine2 atmMachine;
	public NoCard(ATMMachine2 atmMachine){
		this.atmMachine = atmMachine;
	}
	public void insertCard() {
		System.out.println("Please enter the PIN:");
		atmMachine.setAtmState(atmMachine.getHasPin());
	}
	public void ejectCard() {
		System.out.println("Insert card first");
	}
	public void insertPin(int pin) {
		System.out.println("Insert card first");
	}
	public void requestCash(int cash) {
		System.out.println("Insert card first");
	}
}
class HasPin implements ATMState2{
	ATMMachine2 atmMachine;
	public HasPin(ATMMachine2 atmMachine){
		this.atmMachine = atmMachine;
	}
	public void insertCard() {
		System.out.println("You can't insert more then one card");
	}
	public void ejectCard() {
		System.out.println("Card ejected");
		atmMachine.setAtmState(atmMachine.getNoCard());
	}
	public void insertPin(int pin) {
		System.out.println("Already entered PIN");
	}
	public void requestCash(int cash) {
		if(cash > atmMachine.cashInATMMachine){
			System.out.println("Don't have that much cash");
			System.out.println("Card ejected");
			atmMachine.setAtmState(atmMachine.getNoCard());
		}else{
			System.out.println(cash +" Cash is provided by the machine");
			atmMachine.setCashInATMMachine(atmMachine.cashInATMMachine-cash);
			
			System.out.println("Card ejected");
			atmMachine.setAtmState(atmMachine.getNoCard());
			if(atmMachine.cashInATMMachine <= 0){
				System.out.println("No cash in machine");
				atmMachine.setAtmState(atmMachine.getNoCash());
			}
		}
	}
}
class NoCash implements ATMState2{
	ATMMachine2 atmMachine;
	public NoCash(ATMMachine2 atmMachine){
		this.atmMachine = atmMachine;
	}
	public void insertCard() {
		System.out.println("Don't have enough money");
	}
	public void ejectCard() {
		System.out.println("Don't have enough money");		
	}
	public void insertPin(int pin) {
		System.out.println("Don't have enough money");				
	}
	public void requestCash(int cash) {
		System.out.println("Don't have enough money");				
	}
}
