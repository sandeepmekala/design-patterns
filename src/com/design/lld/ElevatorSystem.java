package com.design.lld;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


class Building {
    List<Floor> floorList;

    Building(List<Floor> floors){
        this.floorList = floors;
    }

    public void addFloors(Floor newFloor){
        floorList.add(newFloor);
    }

    public void removeFloors(Floor removeFloor){
        floorList.remove(removeFloor);
    }

    List<Floor> getAllFloorList(){
        return floorList;
    }
}

class Floor {
    int floorNumber;
    ExternalDispatcher externalDispatcher;

    public Floor(int floorNumber){
        this.floorNumber = floorNumber;
        externalDispatcher = new ExternalDispatcher();
    }
    public void pressButton(Direction direction) {

        externalDispatcher.submitExternalRequest(floorNumber, direction);
    }
}

class ExternalDispatcher {

    List<ElevatorController>  elevatorControllerList = ElevatorCreator.elevatorControllerList;

    public void submitExternalRequest(int floor, Direction direction){


        //for simplicity, i am following even odd,
        for(ElevatorController elevatorController : elevatorControllerList) {

           int elevatorID = elevatorController.elevatorCar.id;
           if (elevatorID%2==1 && floor%2==1){
               elevatorController.submitExternalRequest(floor,direction);
           } else if(elevatorID%2==0 && floor%2==0){
               elevatorController.submitExternalRequest(floor,direction);
           }
        }
    }

}

enum Direction {
    UP,
    DOWN;
}

enum ElevatorState {
    MOVING,
    IDLE;
}

class ElevatorCar {
    int id;
    ElevatorDisplay display;
    InternalButtons internalButtons;
    ElevatorState elevatorState;
    int currentFloor;
    Direction elevatorDirection;
    ElevatorDoor elevatorDoor;

    public ElevatorCar(){
        display = new ElevatorDisplay();
        internalButtons = new InternalButtons();
        elevatorState = ElevatorState.IDLE;
        currentFloor = 0;
        elevatorDirection = Direction.UP;
        elevatorDoor = new ElevatorDoor();

    }
    public void showDisplay() {
         display.showDisplay();
    }

    public void pressButton(int destination) {
        internalButtons.pressButton(destination, this);
    }

    public void setDisplay() {
        this.display.setDisplay(currentFloor, elevatorDirection);
    }

    boolean moveElevator(Direction dir, int destinationFloor){
        int startFloor = currentFloor;
        if(dir == Direction.UP) {
            for(int i = startFloor; i<=destinationFloor; i++) {

                this.currentFloor = startFloor;
                setDisplay();
                showDisplay();
                if(i == destinationFloor) {
                    return true;
                }
            }
        }

        if(dir == Direction.DOWN) {
            for(int i = startFloor; i>=destinationFloor; i--) {

                this.currentFloor = startFloor;
                setDisplay();
                showDisplay();
                if(i == destinationFloor) {
                    return true;
                }
            }
        }
        return false;
    }

}

class ElevatorController {
    PriorityQueue<Integer> upMinPQ;
    PriorityQueue<Integer> downMaxPQ;
    ElevatorCar elevatorCar;

    ElevatorController(ElevatorCar elevatorCar){

        this.elevatorCar = elevatorCar;
        upMinPQ = new PriorityQueue<>();
        downMaxPQ = new PriorityQueue<>((a,b) -> b-a);

    }
     public void submitExternalRequest(int floor, Direction direction){

        if(direction == Direction.DOWN) {
            downMaxPQ.offer(floor);
        } else {
            upMinPQ.offer(floor);
        }
     }

    public void submitInternalRequest(int floor){

    }

    public void controlElevator(){
        while(true) {
            if(elevatorCar.elevatorDirection == Direction.UP){

            }
        }
    }
}

class ElevatorCreator {

    static List<ElevatorController> elevatorControllerList = new ArrayList<>();
    static {

        ElevatorCar elevatorCar1 = new ElevatorCar();
        elevatorCar1.id =1;
        ElevatorController controller1 = new ElevatorController(elevatorCar1);

        ElevatorCar elevatorCar2 = new ElevatorCar();
        elevatorCar1.id =2;
        ElevatorController controller2 = new ElevatorController(elevatorCar2);

        elevatorControllerList.add(controller1);
        elevatorControllerList.add(controller2);
    }
}

class ElevatorDisplay {
    int floor;
    Direction direction;

    public void setDisplay(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public void showDisplay(){
        System.out.println(floor);
        System.out.println(direction);
    }
}

class ElevatorDoor {
    public void openDoor(){
        System.out.println("Opening the Elevator door ");
    }

    public void closeDoor(){
        System.out.println("Closing the Elevator door");
    }
}

class InternalButtons {
    InternalDispatcher dispatcher = new InternalDispatcher();

    int[] availableButtons = {1,2,3,4,5,6,7,8,9,10};
    int buttonSelected;

    void pressButton(int destination, ElevatorCar elevatorCar) {

        //1.check if destination is in the list of available floors

        //2.submit the request to the jobDispatcher
        dispatcher.submitInternalRequest(destination, elevatorCar);
    }
}

class InternalDispatcher {
    List<ElevatorController>  elevatorControllerList = ElevatorCreator.elevatorControllerList;

    public void submitInternalRequest(int floor, ElevatorCar elevatorCar){

    }
}

public class ElevatorSystem {
    public static void main(String[] args) {

        List<Floor> floorList = new ArrayList<>();
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        Floor floor3 = new Floor(3);
        Floor floor4 = new Floor(4);
        Floor floor5 = new Floor(5);
        floorList.add(floor1);
        floorList.add(floor2);
        floorList.add(floor3);
        floorList.add(floor4);
        floorList.add(floor5);

        Building building = new Building(floorList);

    }
}
