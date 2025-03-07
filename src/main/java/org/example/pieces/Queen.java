package org.example.pieces;

import org.example.Board;
import org.example.Colour;
import org.example.Position;

public class Queen extends Piece {
    public Queen(Colour colour) {
        super(colour);
    }

    @Override
    protected boolean canPieceMove(Position from, Position to, Board board) {
        return (from.getX() == to.getX() ^ from.getY() == to.getY())
                || Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY());
    }

    @Override
    public String getName() {
        return "Queen";
    }

    @Override
    public char getSymbol() {
        return 'Q';
    }
}
