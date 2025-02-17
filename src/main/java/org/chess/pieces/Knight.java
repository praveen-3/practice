package org.chess.pieces;

import org.chess.model.Color;
import org.chess.model.Position;
import org.chess.board.Board;

public class Knight extends Piece {
    public Knight(Color color, Position position) {
        super(color, position, 'H');
    }
    
    @Override
    public boolean isValidMove(Position destination, Board board) {
        if (!destination.isValid()) return false;
        
        int rowDiff = Math.abs(destination.row() - position.row());
        int colDiff = Math.abs(destination.col() - position.col());
        
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
} 