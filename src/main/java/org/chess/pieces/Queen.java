package org.chess.pieces;

import org.chess.model.Color;
import org.chess.model.Position;
import org.chess.board.Board;

public class Queen extends Piece {
    public Queen(Color color, Position position) {
        super(color, position, 'Q');
    }
    
    @Override
    public boolean isValidMove(Position destination, Board board) {
        if (!destination.isValid()) return false;
        
        int rowDiff = Math.abs(destination.row() - position.row());
        int colDiff = Math.abs(destination.col() - position.col());
        
        // Can move horizontally, vertically, or diagonally
        return rowDiff == 0 || colDiff == 0 || rowDiff == colDiff;
    }
} 