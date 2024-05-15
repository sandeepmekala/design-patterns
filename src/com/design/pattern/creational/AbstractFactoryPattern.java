package com.design.pattern.creational;

import java.io.IOException;

// It is a Creational Pattern
// It is like a factory pattern, but everything is encapsulated, the methods that create the object, the factories that create the objects, the final objects. 
// The final objects contains objects that use strategy pattern
class AbstractFactoryPattern {
	public static void main(String args[]) throws IOException {

		AbstractFactory bankFactory = FactoryCreator.getFactory("Bank");
		AbstractFactory loanFactory = FactoryCreator.getFactory("Loan");	
		
		Bank bank = bankFactory.getBank("HDFC");
		System.out.println(bank.getBankName());

		Loan loan = loanFactory.getLoan("Home");
		double rate = loan.getInterestRate();
		System.out.println(rate);
	}
}

class FactoryCreator {
	public static AbstractFactory getFactory(String choice) {
		if (choice.equalsIgnoreCase("Bank")) {
			return new BankFactory();
		} else if (choice.equalsIgnoreCase("Loan")) {
			return new LoanFactory();
		}
		return null;
	}
}

abstract class AbstractFactory {
	public abstract Bank getBank(String bank);
	public abstract Loan getLoan(String loan);
}

class BankFactory extends AbstractFactory {
	public Bank getBank(String bank) {
		if (bank.equalsIgnoreCase("HDFC")) {
			return new HDFC();
		} else if (bank.equalsIgnoreCase("SBI")) {
			return new SBI();
		}
		return null;
	}

	public Loan getLoan(String loan) {
		return null;
	}
}

class LoanFactory extends AbstractFactory {
	public Bank getBank(String bank) {
		return null;
	}

	public Loan getLoan(String loan) {
		if (loan.equalsIgnoreCase("Home")) {
			return new HomeLoan();
		} else if (loan.equalsIgnoreCase("Education")) {
			return new EducationLoan();
		}
		return null;
	}
}

interface Bank {
	String getBankName();
}

class HDFC implements Bank {
	public String getBankName() {
		return "HDFC BANK";
	}
}

class SBI implements Bank {
	public String getBankName() {
		return "SBI BANK";
	}
}

interface Loan {
	double getInterestRate();
}

class HomeLoan implements Loan {
	double rate = 7.5;
	public double getInterestRate() {
		return rate;
	}
}

class EducationLoan implements Loan {
	double rate = 8.5;
	public double getInterestRate() {
		return rate;
	}
}
