package org.example.pieces;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.Board;
import org.example.Colour;
import org.example.Position;

@Setter
@Getter
@RequiredArgsConstructor
public abstract class Piece {

    private final Colour colour;
    private boolean hasMoved = false;

    protected boolean canJump() {
        return false;
    }

    public abstract String getName();

    public abstract char getSymbol();

    protected abstract boolean canPieceMove(Position from, Position to, Board board);

    public boolean canMove(Position from, Position to, Board board) {
        // common checks for all pieces:
        // 1. cannot move to the same space
        // 2. must have a clear path to the destination (except knights)
        // 3 .cannot capture own pieces
        if (from.equals(to)) {
            return false;
        }
        if (!board.isPathClear(from, to) && !canJump()) {
            return false;
        }
        if (isSamePlayerPiece(to, board)) {
            return false;
        }
        // check individual piece logic
        return canPieceMove(from, to, board);
    }

    protected boolean isSamePlayerPiece(Position position, Board board) {
        return board.getPiece(position) != null && board.getPiece(position).getColour() == colour;
    }
}
