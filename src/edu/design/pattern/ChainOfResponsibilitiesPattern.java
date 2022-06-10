package edu.design.pattern;
 
// This pattern sends data to an object, if that object is not able use it, it sends the same to any other number of other objects that may able to use it
public class ChainOfResponsibilitiesPattern {
	public static void main(String[] args) {
		Chain calChain1 = new AddNumbers();
		Chain calChain2 = new SubNumbers();
		Chain calChain3 = new MulNumbers();
		Chain calChain4 = new DivNumbers();
		
		calChain1.setNextChain(calChain2);
		calChain2.setNextChain(calChain3);
		calChain3.setNextChain(calChain4);
		
		Numbers request = new Numbers(4, 2, "div");
		calChain1.calculate(request);
	}
}
interface Chain{
	public void setNextChain(Chain nextChain);
	public void calculate(Numbers requests);
}
class Numbers{
	private int number1;
	private int number2;
	private String calculationWanter;
	public Numbers(int number1, int number2, String calculationWanter) {
		this.number1 = number1;
		this.number2 = number2;
		this.calculationWanter = calculationWanter;
	}
	public int getNumber1() {
		return number1;
	}
	public int getNumber2() {
		return number2;
	}
	public String getCalculationWanter() {
		return calculationWanter;
	}
}
class AddNumbers implements Chain{
	private Chain nextChain;
	public void setNextChain(Chain nextChain) {
		this.nextChain = nextChain;
	}
	public void calculate(Numbers requests) {
		if(requests.getCalculationWanter().equals("add")){
			System.out.println(requests.getNumber1()+"+"+requests.getNumber2()+"="
		+(requests.getNumber1()+requests.getNumber2()));
		}else{
			this.nextChain.calculate(requests);
		}
	}	
}
class SubNumbers implements Chain{
	private Chain nextChain;
	public void setNextChain(Chain nextChain) {
		this.nextChain = nextChain;
	}
	public void calculate(Numbers requests) {
		if(requests.getCalculationWanter().equals("sub")){
			System.out.println(requests.getNumber1()+"-"+requests.getNumber2()+"="
		+(requests.getNumber1()-requests.getNumber2()));
		}else{
			this.nextChain.calculate(requests);
		}
	}	
}
class MulNumbers implements Chain{
	private Chain nextChain;
	public void setNextChain(Chain nextChain) {
		this.nextChain = nextChain;
	}
	public void calculate(Numbers requests) {
		if(requests.getCalculationWanter().equals("mul")){
			System.out.println(requests.getNumber1()+"*"+requests.getNumber2()+"="
		+(requests.getNumber1()*requests.getNumber2()));
		}else{
			this.nextChain.calculate(requests);
		}
	}	
}
class DivNumbers implements Chain{
	private Chain nextChain;
	public void setNextChain(Chain nextChain) {
		this.nextChain = nextChain;
	}
	public void calculate(Numbers requests) {
		if(requests.getCalculationWanter().equals("div")){
			System.out.println(requests.getNumber1()+"/"+requests.getNumber2()+"="
		+(requests.getNumber1()/requests.getNumber2()));
		}else{
			System.out.println("This works for add, sub, mul and div");
		}
	}	
}
