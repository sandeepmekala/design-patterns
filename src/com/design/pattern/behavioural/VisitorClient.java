package com.design.pattern.behavioural;

// Element Interface:
interface RoomElement {
    void accept(RoomVisitor visitor);
}

// Concrete Elements:
class SingleRoom implements RoomElement {
    @Override
    public void accept(RoomVisitor visitor) {
        visitor.visit(this);
    }
}

class DoubleRoom implements RoomElement {
    @Override
    public void accept(RoomVisitor visitor) {
        visitor.visit(this);
    }
}

// Visitor Interface:
interface RoomVisitor {
    void visit(SingleRoom singleRoom);
    void visit(DoubleRoom doubleRoom);
}

// Concrete Visitors:
class RoomPricingVisitor implements RoomVisitor {
    @Override
    public void visit(SingleRoom singleRoom) {
        System.out.println("Pricing for Single Room: $100");
    }

    @Override
    public void visit(DoubleRoom doubleRoom) {
        System.out.println("Pricing for Double Room: $150");
    }
}

class RoomMaintenanceVisitor implements RoomVisitor {
    @Override
    public void visit(SingleRoom singleRoom) {
        System.out.println("Maintenance for Single Room completed.");
    }

    @Override
    public void visit(DoubleRoom doubleRoom) {
        System.out.println("Maintenance for Double Room completed.");
    }
}
// Execution:
class VisitorClient {
    public static void main(String[] args) {
        RoomElement singleRoom = new SingleRoom();
        RoomElement doubleRoom = new DoubleRoom();

        RoomVisitor pricingVisitor = new RoomPricingVisitor();
        RoomVisitor maintenanceVisitor = new RoomMaintenanceVisitor();

        singleRoom.accept(pricingVisitor);
        singleRoom.accept(maintenanceVisitor);

        doubleRoom.accept(pricingVisitor);
        doubleRoom.accept(maintenanceVisitor);
    }
}
