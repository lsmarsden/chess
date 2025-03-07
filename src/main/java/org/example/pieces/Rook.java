package org.example.pieces;

import org.example.Board;
import org.example.Colour;
import org.example.Position;

public class Rook extends Piece {
    public Rook(Colour colour) {
        super(colour);
    }

    @Override
    protected boolean canPieceMove(Position from, Position to, Board board) {

        return (from.getX() == to.getX() ^ from.getY() == to.getY());
    }

    @Override
    public String getName() {
        return "Rook";
    }

    @Override
    public char getSymbol() {
        return 'R';
    }
}
