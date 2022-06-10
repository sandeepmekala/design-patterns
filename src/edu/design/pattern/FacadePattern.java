package edu.design.pattern;

// It is a Behavioral pattern
// When you create simplified interface that performs many other actions behind the scenes
// In this example only deposit and withdraw are exposed to customer but behind the scenes we perform many actions to validate 
public class FacadePattern {

	public static void main(String[] args) {
		BankAccountFacade bankAccountFacade = new BankAccountFacade(12345678);

		bankAccountFacade.withdraw(50.0);
		bankAccountFacade.withdraw(900.0);
		bankAccountFacade.deposit(200.0);
	}
}

class BankAccountFacade {
	private int accountNumber;

	private CheckAccountNumber accountNumberChecker;
	private FundManager fundManager;

	public BankAccountFacade(int accountNumber) {
		this.accountNumber = accountNumber;

		this.accountNumberChecker = new CheckAccountNumber();
		this.fundManager = new FundManager();
	}
	private int getAccountNumber() {
		return accountNumber;
	}
	public void deposit(double depositeAmt) {
		if (accountNumberChecker.checkAccountNumber(getAccountNumber())) {
			fundManager.depositeAmt(depositeAmt);
			System.out.println("Transaction completed:");
		} else {
			System.out.println("Transaction failed:");
		}
	}
	public void withdraw(double withdrawAmount) {
		if (accountNumberChecker.checkAccountNumber(getAccountNumber()) && 
				fundManager.checkBalance(withdrawAmount)) {
			fundManager.withdrawAmt(withdrawAmount);
			System.out.println("Transaction completed:");
		} else {
			System.out.println("Transaction failed:");
		}
	}
}

class CheckAccountNumber {
	private int accountNumber = 12345678;

	private int getAccountNumber() {
		return accountNumber;
	}

	public boolean checkAccountNumber(int accountNumber) {
		if (accountNumber == getAccountNumber())
			return true;
		else
			return false;
	}
}

class FundManager {
	private double balance = 1000.0;

	public boolean checkBalance(double withdrawAmount) {
		if (withdrawAmount > balance) {
			System.out.println("Error: You don't have enough money to with draw: ");
			System.out.println("Current Balance: "+ balance);
			return false;
		} else {
			return true;
		}
	}

	public void depositeAmt(double depositeAmt) {
		balance += depositeAmt;
		System.out.println("Deposite Completed: Current Balance: "+ balance);
	}

	public void withdrawAmt(double withdrawAmount) {
		balance -= withdrawAmount;
		System.out.println("Withdrawal Completed: Current Balance: "+ balance);
	}
}
