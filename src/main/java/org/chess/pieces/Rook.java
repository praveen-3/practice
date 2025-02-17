package org.chess.pieces;

import org.chess.model.Color;
import org.chess.model.Position;
import org.chess.board.Board;

public class Rook extends Piece {
    public Rook(Color color, Position position) {
        super(color, position, 'R');
    }
    
    @Override
    public boolean isValidMove(Position destination, Board board) {
        if (!destination.isValid()) return false;
        
        int rowDiff = Math.abs(destination.row() - position.row());
        int colDiff = Math.abs(destination.col() - position.col());
        
        // Rook can move only horizontally or vertically
        return (rowDiff == 0 && colDiff > 0) || (colDiff == 0 && rowDiff > 0);
    }
} 