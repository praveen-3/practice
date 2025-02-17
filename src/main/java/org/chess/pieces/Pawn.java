package org.chess.pieces;

import org.chess.model.Color;
import org.chess.model.Position;
import org.chess.board.Board;

public class Pawn extends Piece {
    public Pawn(Color color, Position position) {
        super(color, position, 'P');
    }
    
    @Override
    public boolean isValidMove(Position destination, Board board) {
        if (!destination.isValid()) return false;
        
        int direction = (color == Color.WHITE) ? 1 : -1;
        int rowDiff = destination.row() - position.row();
        int colDiff = Math.abs(destination.col() - position.col());
        
        // Check if there's a piece at the destination
        Piece targetPiece = board.getPiece(destination);
        
        // Moving to capture
        if (targetPiece != null) {
            return colDiff == 1 && rowDiff == direction;
        }
        
        // Moving forward to empty square
        return colDiff == 0 && rowDiff == direction;
    }
} 