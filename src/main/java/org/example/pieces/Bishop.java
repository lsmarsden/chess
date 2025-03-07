package org.example.pieces;

import org.example.Board;
import org.example.Colour;
import org.example.Position;

public class Bishop extends Piece {
    public Bishop(Colour colour) {
        super(colour);
    }

    @Override
    protected boolean canPieceMove(Position from, Position to, Board board) {
        return Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY());
    }

    @Override
    public String getName() {
        return "Bishop";
    }


    @Override
    public char getSymbol() {
        return 'B';
    }
}
