package org.chess.pieces;

import org.chess.model.Color;
import org.chess.model.Position;
import org.chess.board.Board;

public class King extends Piece {
    public King(Color color, Position position) {
        super(color, position, 'K');
    }
    
    @Override
    public boolean isValidMove(Position destination, Board board) {
        if (!destination.isValid()) return false;
        
        int rowDiff = Math.abs(destination.row() - position.row());
        int colDiff = Math.abs(destination.col() - position.col());
        
        return rowDiff <= 1 && colDiff <= 1;
    }
} 