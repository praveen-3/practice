package org.chess.pieces;

import org.chess.model.Color;
import org.chess.model.Position;
import org.chess.board.Board;

public abstract class Piece {
    protected Color color;
    protected Position position;
    protected final char type;
    
    public Piece(Color color, Position position, char type) {
        this.color = color;
        this.position = position;
        this.type = type;
    }
    
    public abstract boolean isValidMove(Position destination, Board board);
    
    public Color getColor() {
        return color;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public void setPosition(Position position) {
        this.position = position;
    }
    
    @Override
    public String toString() {
        return "" + (color == Color.WHITE ? 'W' : 'B') + type;
    }
} 