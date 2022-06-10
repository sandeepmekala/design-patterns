package edu.design.pattern;

import java.io.IOException;

// It is a Creational Pattern
class AbstractFactoryPattern {
	public static void main(String args[]) throws IOException {

		AbstractFactory bankFactory = FactoryCreator.getFactory("Bank");
		AbstractFactory loanFactory = FactoryCreator.getFactory("Loan");
		
		String bankName = "HDFC";
		String loanName = "Home";
		double rate = 8.0;
		double loanAmount = 10000.0;
		int years = 5;
		
		Bank bank = bankFactory.getBank(bankName);
		Loan loan = loanFactory.getLoan(loanName);

		loan.getInterestRate(rate);
		loan.calculateLoanPayment(loanAmount, years);
	}
}

interface Bank {
	String getBankName();
}

class HDFC implements Bank {
	private final String bankName;

	public HDFC() {
		bankName = "HDFC BANK";
	}

	public String getBankName() {
		return bankName;
	}
}

class SBI implements Bank {
	private final String BNAME;

	public SBI() {
		BNAME = "SBI BANK";
	}

	public String getBankName() {
		return BNAME;
	}
}

abstract class Loan {
	protected double rate;

	abstract void getInterestRate(double rate);

	public void calculateLoanPayment(double loanamount, int years) {
		double EMI;
		int n = years * 12;
		rate = rate / 1200;
		EMI = ((rate * Math.pow((1 + rate), n)) / ((Math.pow((1 + rate), n)) - 1)) * loanamount;

		System.out.println("your monthly EMI is " + EMI + " for the amount" + loanamount + " you have borrowed");
	}
}

class HomeLoan extends Loan {
	public void getInterestRate(double r) {
		rate = r;
	}
}

class EducationLoan extends Loan {
	public void getInterestRate(double r) {
		rate = r;
	}
}

class BankFactory extends AbstractFactory {
	public Bank getBank(String bank) {
		if (bank == null) {
			return null;
		}
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
		if (loan == null) {
			return null;
		}
		if (loan.equalsIgnoreCase("Home")) {
			return new HomeLoan();
		} else if (loan.equalsIgnoreCase("Education")) {
			return new EducationLoan();
		}
		return null;
	}

}

abstract class AbstractFactory {
	public abstract Bank getBank(String bank);
	public abstract Loan getLoan(String loan);
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
