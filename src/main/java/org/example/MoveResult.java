package org.example;

import org.example.pieces.Piece;

public record MoveResult(Move move, Piece movedPiece, Piece capturedPiece) {
}
