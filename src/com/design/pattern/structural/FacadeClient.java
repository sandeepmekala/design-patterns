package com.design.pattern.structural;

// It is a Structural pattern
// It is a refactoring pattern
// Make code clean
// Create simplified interface for lot of actions
// In this example only deposit and withdraw are exposed to customer but behind the scenes we perform many actions to validate public class FacadeClient {
// Subsystem class 1
class Product {
    public void getProductDetails() {
        /* Fetch product logic */ }
}

class Payment {
    public void processPayment() {
        /* Payment logic */ }
}

class Invoice {
    public void generateInvoice() {
        /* Invoice logic */ }
}

class Notification {
    public void sendNotification() {
        /* Notification logic */ }
}

class OrderFacade {
    private Product product;
    private Payment payment;
    private Invoice invoice;
    private Notification notification;

    public OrderFacade() {
        this.product = new Product();
        this.payment = new Payment();
        this.invoice = new Invoice();
        this.notification = new Notification();
    }

    public void createOrder() {
        product.getProductDetails();
        payment.processPayment();
        invoice.generateInvoice();
        notification.sendNotification();
    }
}

// Client Code
class FacadeClient {
    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.createOrder();
    }
}