package com.design.pattern.behavioural;

import java.util.ArrayList;
import java.util.List;

interface Observable {
    void add(Observer observer);
    void remove(Observer observer);
    void notifyObservers();
}

class ProductStock implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private String productName;
    private int stockCount;

    public void add(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(productName, stockCount);
        }
    }

    public void setStock(String productName, int stockCount) {
        this.productName = productName;
        this.stockCount = stockCount;
        notifyObservers(); // Notify all observers when stock changes
    }
}

interface Observer {
    void update(String productName, int stockCount);
}

class EmailNotifier implements Observer {
    private String email;

    public EmailNotifier(String email) {
        this.email = email;
    }

    @Override
    public void update(String productName, int stockCount) {
        System.out.println("Email sent to " + email + ": " + productName + " is back in stock with " + stockCount + " units.");
    }
}

class MobileNotifier implements Observer {
    private String phoneNumber;

    public MobileNotifier(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String productName, int stockCount) {
        System.out.println("SMS sent to " + phoneNumber + ": " + productName + " is back in stock with " + stockCount + " units.");
    }
}

// Example Usage
public class ObserverClient {
    public static void main(String[] args) {
        ProductStock iphoneStock = new ProductStock();
        Observer emailUser = new EmailNotifier("user@example.com");
        Observer mobileUser = new MobileNotifier("1234567890");

        iphoneStock.add(emailUser);
        iphoneStock.add(mobileUser);

        // Update stock and notify users
        iphoneStock.setStock("iPhone", 100);
    }
}
