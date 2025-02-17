package org.chess.pieces;

import org.chess.model.Color;
import org.chess.model.Position;
import org.chess.board.Board;

public class Bishop extends Piece {
    public Bishop(Color color, Position position) {
        super(color, position, 'B');
    }
    
    @Override
    public boolean isValidMove(Position destination, Board board) {
        if (!destination.isValid()) return false;
        
        int rowDiff = Math.abs(destination.row() - position.row());
        int colDiff = Math.abs(destination.col() - position.col());
        
        // Bishop can only move diagonally
        return rowDiff == colDiff && rowDiff > 0;
    }
} 