package org.chess.game;

import org.chess.model.*;
import org.chess.board.Board;
import org.chess.pieces.*;

public class ChessGame {
    private final Board board;
    private Color currentTurn;
    private GameStatus status;
    
    public ChessGame(String[][] initialBoard) {
        this.board = new Board(initialBoard);
        this.currentTurn = Color.WHITE;
        this.status = GameStatus.IN_PROGRESS;
    }
    
    public String move(int startRow, int startCol, int endRow, int endCol) {
        Position from = new Position(startRow, startCol);
        Position to = new Position(endRow, endCol);
        
        Piece piece = board.getPiece(from);
        
        if (piece == null || piece.getColor() != currentTurn || !isValidMove(from, to)) {
            return "invalid";
        }
        
        Piece capturedPiece = board.getPiece(to);
        String result = "";
        
        if (capturedPiece != null) {
            result = capturedPiece.toString();
            if (capturedPiece instanceof King) {
                status = (capturedPiece.getColor() == Color.BLACK) ? 
                    GameStatus.WHITE_WON : GameStatus.BLACK_WON;
            }
        }
        
        board.movePiece(from, to);
        currentTurn = currentTurn.opposite();
        
        return result;
    }
    
    private boolean isValidMove(Position from, Position to) {
        Piece piece = board.getPiece(from);
        Piece targetPiece = board.getPiece(to);
        
        return piece.isValidMove(to, board) &&
               (targetPiece == null || targetPiece.getColor() != piece.getColor());
    }
    
    public int getGameStatus() {
        return switch (status) {
            case IN_PROGRESS -> 0;
            case WHITE_WON -> 1;
            case BLACK_WON -> 2;
        };
    }
    
    public int getNextTurn() {
        if (status != GameStatus.IN_PROGRESS) return -1;
        return currentTurn == Color.WHITE ? 0 : 1;
    }
} 