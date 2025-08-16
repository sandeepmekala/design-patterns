package com.design.lld;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

class Dice {
    int diceCount;
    int min = 1;
    int max =6;

    public Dice(int diceCount){
        this.diceCount = diceCount;
    }

    public int rollDice(){

        int totalSum=0;
        int diceUsed=0;

        while(diceUsed<diceCount){

            totalSum += ThreadLocalRandom.current().nextInt(min,max+1);
            diceUsed++;
        }

        return totalSum;
    }
}

// Snake and Ladder has same structure.
class Jump {
    int start;
    int end;

    //getters and setters
}

class Player {
    String id;
    int currentPosition;

    public Player(String id, int currentPosition) {
        this.id = id;
        this.currentPosition = currentPosition;
    }

    //getters and setters
}

class Cell {
    Jump jump;
    //getters and setters
}

class Board {
    Cell[][] cells;

    Board(int boardSize, int numberOfSnakes, int numberOfLadders) {

        initializeCells(boardSize);
        addSnakesLadders(cells, numberOfSnakes, numberOfLadders);
    }

    private void initializeCells(int boardSize) {

        cells = new Cell[boardSize][boardSize];

        for(int i=0;i<boardSize;i++) {
            for(int j=0; j<boardSize;j++) {
                Cell cellObj = new Cell();
                cells[i][j] = cellObj;
            }
        }
    }
    private void addSnakesLadders(Cell[][] cells, int numberOfSnakes, int numberOfLadders){

        while(numberOfSnakes > 0) {
           int snakeHead = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
           int snakeTail = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
           if(snakeTail >= snakeHead) {
               continue;
           }

           Jump snakeObj = new Jump();
           snakeObj.start = snakeHead;
           snakeObj.end = snakeTail;

           Cell cell = getCell(snakeHead);
           cell.jump = snakeObj;

            numberOfSnakes--;
        }

        while(numberOfLadders > 0) {
            int ladderStart = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            int ladderEnd = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            if(ladderStart >= ladderEnd) {
                continue;
            }

            Jump ladderObj = new Jump();
            ladderObj.start = ladderStart;
            ladderObj.end = ladderEnd;

            Cell cell = getCell(ladderStart);
            cell.jump = ladderObj;

            numberOfLadders--;
        }

    }

    Cell getCell(int playerPosition) {
        int boardRow = playerPosition / cells.length;
        int boardColumn = (playerPosition % cells.length);
        return cells[boardRow][boardColumn];
    }
}

class Game {
    Board board;
    Dice dice;
    Deque<Player> playersList = new LinkedList<>();
    Player winner;

    public Game(){
        initializeGame();
    }

    private void initializeGame() {
        board = new Board(10, 5,4);
        dice = new Dice(1);
        winner = null;
        addPlayers();
    }

    private void addPlayers() {
        Player player1 = new Player("p1", 0);
        Player player2 = new Player("p2", 0);
        playersList.add(player1);
        playersList.add(player2);
    }

    public void startGame(){
        while(winner == null) {

            //check whose turn now
            Player playerTurn = findPlayerTurn();
            System.out.println("player turn is:" + playerTurn.id + " current position is: " + playerTurn.currentPosition);

            //roll the dice
            int diceNumbers = dice.rollDice();

            //get the new position
            int playerNewPosition = playerTurn.currentPosition + diceNumbers;
            playerNewPosition = jumpCheck(playerNewPosition);
            playerTurn.currentPosition = playerNewPosition;

            System.out.println("player turn is:" + playerTurn.id + " new Position is: " + playerNewPosition);
            //check for winning condition
            if(playerNewPosition >= board.cells.length * board.cells.length-1){

                winner = playerTurn;
            }

        }

        System.out.println("WINNER IS:" + winner.id);
    }


    private Player findPlayerTurn() {

        Player playerTurns = playersList.removeFirst();
        playersList.addLast(playerTurns);
        return playerTurns;
    }

    private int jumpCheck (int playerNewPosition) {

        if(playerNewPosition > board.cells.length * board.cells.length-1 ){
            return playerNewPosition;
        }

        Cell cell = board.getCell(playerNewPosition);
        if(cell.jump != null && cell.jump.start == playerNewPosition) {
            String jumpBy = (cell.jump.start < cell.jump.end)? "ladder" : "snake";
            System.out.println("jump done by: " + jumpBy);
            return cell.jump.end;
        }
        return playerNewPosition;
    }
}


public class SnakeAndLander {
    public static void main(String[] args) {

        Game obj = new Game();
        obj.startGame();
    }

}
