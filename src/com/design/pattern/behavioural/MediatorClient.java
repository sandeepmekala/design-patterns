package com.design.pattern.behavioural;

import java.util.ArrayList;
import java.util.List;
   
// It is used to handle communication between related objects(Colleagues)
// Allows loose coupling by encaptulating the way disperates sets of objects interact with each other. Allows for the actions of each object set to vary independently of one another.

// Step 1: Colleague Interface
interface Colleague {
    void placeBid(int amount);
    void receiveNotification(String message);
    String getName();
}

// Step 2: Concrete Colleague
class Bidder implements Colleague {
    private String name;
    private Mediator mediator;

    public Bidder(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
        mediator.addBidder(this); // Add bidder to the mediator
    }

    public void placeBid(int amount) {
        mediator.notifyBidders(this, amount);
    }

    public void receiveNotification(String message) {
        System.out.println(name + " received notification: " + message);
    }

    public String getName() {
        return name;
    }
}

// Step 3: Mediator Interface
interface Mediator {
    void addBidder(Colleague bidder);
    void notifyBidders(Colleague originator, int amount);
}

// Step 4: Concrete Mediator
class AuctionMediator implements Mediator {
    private List<Colleague> bidders;

    public AuctionMediator() {
        this.bidders = new ArrayList<>();
    }

    public void addBidder(Colleague bidder) {
        bidders.add(bidder);
    }

    public void notifyBidders(Colleague originator, int amount) {
        for (Colleague bidder : bidders) {
            if (!bidder.equals(originator)) {
                bidder.receiveNotification(
                    originator.getName() + " placed a bid of: " + amount
                );
            }
        }
    }
}

// Step 5: Main Class
public class MediatorClient {
    public static void main(String[] args) {
        Mediator auctionMediator = new AuctionMediator();

        Colleague bidder1 = new Bidder("Alice", auctionMediator);
        Colleague bidder2 = new Bidder("Bob", auctionMediator);
        Colleague bidder3 = new Bidder("Charlie", auctionMediator);

        bidder1.placeBid(1000);
        bidder2.placeBid(1500);
        bidder3.placeBid(2000);
    }
}