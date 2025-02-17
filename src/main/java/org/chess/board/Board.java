package org.chess.board;

import org.chess.model.Position;
import org.chess.pieces.*;
import org.chess.model.Color;

public class Board {
    private Piece[][] board;
    
    public Board(String[][] initialBoard) {
        board = new Piece[8][8];
        initializeBoard(initialBoard);
    }
    
    private void initializeBoard(String[][] initialBoard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String piece = initialBoard[i][j];
                if (!piece.isEmpty()) {
                    board[i][j] = createPiece(piece, new Position(i, j));
                }
            }
        }
    }
    
    private Piece createPiece(String pieceStr, Position pos) {
        Color color = pieceStr.charAt(0) == 'W' ? Color.WHITE : Color.BLACK;
        char type = pieceStr.charAt(1);
        
        return switch (type) {
            case 'K' -> new King(color, pos);
            case 'Q' -> new Queen(color, pos);
            case 'R' -> new Rook(color, pos);
            case 'B' -> new Bishop(color, pos);
            case 'H' -> new Knight(color, pos);
            case 'P' -> new Pawn(color, pos);
            default -> throw new IllegalArgumentException("Invalid piece type: " + type);
        };
    }
    
    public Piece getPiece(Position position) {
        return board[position.row()][position.col()];
    }
    
    public void movePiece(Position from, Position to) {
        Piece piece = getPiece(from);
        piece.setPosition(to);
        board[to.row()][to.col()] = piece;
        board[from.row()][from.col()] = null;
    }
} 