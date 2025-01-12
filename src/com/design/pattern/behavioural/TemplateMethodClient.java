package com.design.pattern.behavioural;
 
// Used to create a group of subclassses that have to execute similar group of methods
// You can create an abstract class that contains a method called the template
// The template method will have series of method calls that every subclass object call
// The subclass objects can override some of the method calls
// Abstract class defining the template
abstract class PaymentFlow {
    
    // Template method - defines the algorithm structure
    public final void sendMoney() {
        validateRequest();
        debitAmount();
        calculateFees();
        creditAmount();
    }
    
    // Steps to be implemented by subclasses
    protected abstract void validateRequest();
    protected abstract void debitAmount();
    protected abstract void calculateFees();
    protected abstract void creditAmount();
}

// Concrete implementation for Pay to Friend flow
class PayToFriendFlow extends PaymentFlow {
    
    @Override
    protected void validateRequest() {
        System.out.println("Validating request for Pay to Friend...");
    }
    
    @Override
    protected void debitAmount() {
        System.out.println("Debiting amount from sender for Pay to Friend...");
    }
    
    @Override
    protected void calculateFees() {
        System.out.println("No fees for Pay to Friend.");
    }
    
    @Override
    protected void creditAmount() {
        System.out.println("Crediting full amount to the friend.");
    }
}

// Concrete implementation for Pay to Merchant flow
class PayToMerchantFlow extends PaymentFlow {
    
    @Override
    protected void validateRequest() {
        System.out.println("Validating request for Pay to Merchant...");
    }
    
    @Override
    protected void debitAmount() {
        System.out.println("Debiting amount from sender for Pay to Merchant...");
    }
    
    @Override
    protected void calculateFees() {
        System.out.println("Calculating 2% fees for Pay to Merchant...");
    }
    
    @Override
    protected void creditAmount() {
        System.out.println("Crediting remaining amount to the merchant.");
    }
}

// Client Code
public class TemplateMethodClient {
    public static void main(String[] args) {
        // Pay to Friend Flow
        PaymentFlow payToFriend = new PayToFriendFlow();
        System.out.println("Executing Pay to Friend Flow:");
        payToFriend.sendMoney();
        
        System.out.println();

        // Pay to Merchant Flow
        PaymentFlow payToMerchant = new PayToMerchantFlow();
        System.out.println("Executing Pay to Merchant Flow:");
        payToMerchant.sendMoney();
    }
}
