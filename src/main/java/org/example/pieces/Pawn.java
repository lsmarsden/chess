package org.example.pieces;

import org.example.Board;
import org.example.Colour;
import org.example.Position;

public class Pawn extends Piece {

    public Pawn(Colour colour) {
        super(colour);
    }

    @Override
    protected boolean canPieceMove(Position from, Position to, Board board) {
        int direction = getColour() == Colour.BLACK ? 1 : -1;
        int maxDistance = isHasMoved() ? 1 : 2;
        int distance = (to.getX() - from.getX()) * direction;
        Piece destinationPiece = board.getPiece(to);

        // forward move
        if (to.getY() == from.getY()) {
            return distance > 0 && distance <= maxDistance && destinationPiece == null;
        } else {
            // diagonal capture
            return distance == 1 && Math.abs(to.getY() - from.getY()) == 1 && destinationPiece != null && destinationPiece.getColour() != getColour();
        }
    }

    @Override
    public String getName() {
        return "Pawn";
    }

    @Override
    public char getSymbol() {
        return 'P';
    }
}
