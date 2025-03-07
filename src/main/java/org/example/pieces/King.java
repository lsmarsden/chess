package org.example.pieces;

import org.example.Board;
import org.example.Colour;
import org.example.Position;

public class King extends Piece {
    public King(Colour colour) {
        super(colour);
    }

    @Override
    protected boolean canPieceMove(Position from, Position to, Board board) {
        return Math.abs(from.getX() - to.getX()) <= 1 && Math.abs(from.getY() - to.getY()) <= 1;
    }

    @Override
    public String getName() {
        return "King";
    }

    @Override
    public char getSymbol() {
        return 'K';
    }
}
