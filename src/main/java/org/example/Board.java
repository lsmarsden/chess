package org.example;

import org.example.pieces.Bishop;
import org.example.pieces.King;
import org.example.pieces.Knight;
import org.example.pieces.Pawn;
import org.example.pieces.Piece;
import org.example.pieces.Queen;
import org.example.pieces.Rook;

public class Board {
    private final Piece[][] board = new Piece[8][8];

    public Board() {
        setupPieces();
    }

    private void setupPieces() {
        board[0][0] = new Rook(Colour.BLACK);
        board[0][1] = new Knight(Colour.BLACK);
        board[0][2] = new Bishop(Colour.BLACK);
        board[0][3] = new Queen(Colour.BLACK);
        board[0][4] = new King(Colour.BLACK);
        board[0][5] = new Bishop(Colour.BLACK);
        board[0][6] = new Knight(Colour.BLACK);
        board[0][7] = new Rook(Colour.BLACK);

        board[7][0] = new Rook(Colour.WHITE);
        board[7][1] = new Knight(Colour.WHITE);
        board[7][2] = new Bishop(Colour.WHITE);
        board[7][3] = new Queen(Colour.WHITE);
        board[7][4] = new King(Colour.WHITE);
        board[7][5] = new Bishop(Colour.WHITE);
        board[7][6] = new Knight(Colour.WHITE);
        board[7][7] = new Rook(Colour.WHITE);

        for (int i = 0; i < board.length; i++) {
            board[1][i] = new Pawn(Colour.BLACK);
            board[6][i] = new Pawn(Colour.WHITE);
        }
    }

    public Piece getPiece(Position position) {
        return board[position.getX()][position.getY()];
    }

    public void setPiece(Position position, Piece piece) {
        board[position.getX()][position.getY()] = piece;
    }

    public MoveResult move(Move move) {
        Position from = move.from();
        Position to = move.to();
        Piece movedPiece = getPiece(from);
        Piece capturedPiece = getPiece(to);
        setPiece(to, movedPiece);
        setPiece(from, null);

        return new MoveResult(move, movedPiece, capturedPiece);
    }

    public void undoMove(MoveResult moveResult) {
        Move move = moveResult.move();
        setPiece(move.from(), moveResult.movedPiece());
        setPiece(move.to(), moveResult.capturedPiece());
    }

    public boolean isValidMove(Move move) {
        Position from = move.from();
        Position to = move.to();
        Piece piece = getPiece(from);

        if (piece == null || piece.getColour() != move.player()) {
            return false;
        }
        if (!piece.canMove(from, to, this)) {
            return false;
        }
        if (getPiece(to) != null && getPiece(to).getColour() == move.player()) {
            return false; // Can't capture own piece
        }

        return true;
    }

    public boolean isCheck(Colour player) {
        Position kingPosition = findKing(player);
        return isSquareUnderAttack(kingPosition, player);
    }

    public boolean isCheckmate(Colour player) {
        if (!isCheck(player)) {
            return false;
        }
        return false;
    }

    public boolean isPathClear(Position from, Position to) {

        // if the path is not a direct line horizontally, vertically, or diagonally,
        // then don't consider it as a clear path, as there is no straight line
        if (from.getX() != to.getX() && from.getY() != to.getY() && Math.abs(from.getX() - to.getX()) != Math.abs(from.getY() - to.getY())) {
            return false;
        }

        int rowStep = Integer.compare(to.getX(), from.getX());  // -1, 0, or 1
        int colStep = Integer.compare(to.getY(), from.getY());  // -1, 0, or 1

        int row = from.getX() + rowStep;
        int col = from.getY() + colStep;

        while (row != to.getX() || col != to.getY()) {
            if (getPiece(new Position(row, col)) != null) {
                return false;  // A piece is blocking the way
            }
            row += rowStep;
            col += colStep;
        }
        return true;  // Path is clear
    }

    private Position findKing(Colour player) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] instanceof King k && k.getColour() == player) {
                    return new Position(i, j);
                }
            }
        }
        throw new IllegalStateException("King not found");
    }

    private boolean isSquareUnderAttack(Position pos, Colour player) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece != null
                        && piece.getColour() != player
                        && piece.canMove(new Position(i, j), pos, this)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int row = 0;
        for (Piece[] pieces : board) {
            sb.append(row++).append(" ");
            for (Piece piece : pieces) {
                if (piece != null) {
                    sb.append(piece.getSymbol());
                } else {
                    sb.append("-");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.append("  ");
        for (int i = 0; i < 8; i++) {
            sb.append(i).append(" ");
        }
        return sb.toString();
    }
}
