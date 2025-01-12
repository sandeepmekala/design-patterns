package com.design.lld;

import java.util.Scanner;

class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameStatus status;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.board = new Board();
        this.status = GameStatus.ACTIVE;
        board.initializeBoard();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (status == GameStatus.ACTIVE) {
            System.out.println("Enter start x, y and end x, y:");
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            int endX = scanner.nextInt();
            int endY = scanner.nextInt();

            if (!makeMove(startX, startY, endX, endY)) {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.close();
    }

    public boolean makeMove(int startX, int startY, int endX, int endY) {
        Cell startCell = board.getCell(startX, startY);
        Cell endCell = board.getCell(endX, endY);

        if (!startCell.isOccupied() || startCell.getPiece().getColor() != currentPlayer.getColor()) {
            return false; // Invalid move
        }

        Piece piece = startCell.getPiece();
        if (piece.isValidMove(startCell, endCell, board)) {
            endCell.setPiece(piece);
            startCell.setPiece(null);
            // Check game status
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
            checkStatus();
            return true;
        }

        return false;
    }

    // Check the status of the game
    public void checkStatus() {
        boolean whiteKingAlive = false;
        boolean blackKingAlive = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Cell cell = board.getCell(i, j);
                if (cell.isOccupied()) {
                    Piece piece = cell.getPiece();
                    if (piece instanceof King) {
                        if (piece.getColor() == PieceColor.WHITE) {
                            whiteKingAlive = true;
                        } else if (piece.getColor() == PieceColor.BLACK) {
                            blackKingAlive = true;
                        }
                    }
                }
            }
        }

        if (!whiteKingAlive) {
            status = GameStatus.BLACK_WINS;
            System.out.println("Game Over: Black wins!");
        } else if (!blackKingAlive) {
            status = GameStatus.WHITE_WINS;
            System.out.println("Game Over: White wins!");
        } else if (isStalemate()) {
            status = GameStatus.DRAW;
            System.out.println("Game Over: It's a draw!");
        } else {
            status = GameStatus.ACTIVE;
        }
    }

    // A simple stalemate check (can be enhanced with additional rules)
    private boolean isStalemate() {
        // Logic to check if no legal moves exist for either player
        return false; // Placeholder for simplicity
    }

}

class Board {
    private final int SIZE = 8;
    private Cell[][] cells;

    public Board() {
        cells = new Cell[SIZE][SIZE];
    }

    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new Cell(i, j, null);
            }
        }

        // Initialize pawns
        for (int i = 0; i < SIZE; i++) {
            cells[1][i].setPiece(new Pawn(PieceColor.BLACK));
            cells[6][i].setPiece(new Pawn(PieceColor.WHITE));
        }

        // Initialize other pieces
        // Example for Rook
        cells[0][0].setPiece(new Rook(PieceColor.BLACK));
        cells[0][7].setPiece(new Rook(PieceColor.BLACK));
        cells[7][0].setPiece(new Rook(PieceColor.WHITE));
        cells[7][7].setPiece(new Rook(PieceColor.WHITE));
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }
}

class Cell {
    private int x;
    private int y;
    private Piece piece;

    public Cell(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

abstract class Piece {
    protected PieceColor color;
    protected boolean isAlive;

    public Piece(PieceColor color) {
        this.color = color;
        this.isAlive = true;
    }

    public PieceColor getColor() {
        return color;
    }

    public abstract boolean isValidMove(Cell start, Cell end, Board board);
}

class Rook extends Piece {
    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Cell start, Cell end, Board board) {
        // Rook moves horizontally or vertically
        return (start.getX() == end.getX() || start.getY() == end.getY());
    }
}

class Pawn extends Piece {
    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Cell start, Cell end, Board board) {
        // Rook moves horizontally or vertically
        return true;
    }
}
class King extends Piece {
    public King(PieceColor color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Cell start, Cell end, Board board) {
        // Rook moves horizontally or vertically
        return true;
    }
}

class Player {
    private String name;
    private PieceColor color;

    public Player(String name, PieceColor color) {
        this.name = name;
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }
}

enum PieceColor {
    WHITE, BLACK;
}

enum GameStatus {
    ACTIVE, DRAW, BLACK_WINS, WHITE_WINS;
}

public class ChessGame {
    public static void main(String[] args) {
        Player player1 = new Player("Alice", PieceColor.WHITE);
        Player player2 = new Player("Bob", PieceColor.BLACK);
        Game game = new Game(player1, player2);
        game.start();

    }
    
}

