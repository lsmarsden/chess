package org.example.pieces;

import org.example.Colour;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

class KnightTest extends PieceTestBase<Knight> {

    private Knight underTest = new Knight(Colour.WHITE);

    @BeforeEach
    void setUp() {
        super.setup(underTest);
        startPosition = new Position(4, 4);
    }

    @Override
    protected String expectedName() {
        return "Knight";
    }
    @Override
    protected char expectedSymbol() {
        return 'N';
    }
    @Override
    protected boolean shouldBeAbleToJump() {
        return true;
    }

    @Nested
    class White {

        @BeforeEach
        void setup() {
            underTest = new Knight(Colour.WHITE);
            KnightTest.this.setup(underTest);
            board.setPiece(startPosition, underTest);
        }

        @Test
        void testUnobstructedMovement() {
            // set up
            // surround knight and validate can still move
            board.setPiece(new Position(3, 3), new Knight(Colour.BLACK));
            board.setPiece(new Position(3, 4), new Knight(Colour.BLACK));
            board.setPiece(new Position(3, 5), new Knight(Colour.BLACK));
            board.setPiece(new Position(4, 3), new Knight(Colour.BLACK));
            board.setPiece(new Position(4, 5), new Knight(Colour.BLACK));
            board.setPiece(new Position(5, 3), new Knight(Colour.BLACK));
            board.setPiece(new Position(5, 4), new Knight(Colour.BLACK));
            board.setPiece(new Position(5, 5), new Knight(Colour.BLACK));
            clearPositions(List.of(new Position(6, 3), new Position(6, 5)));

            // The knight should be able to move in an "L" shape regardless of obstructions
            verifyValidMoves(
                    new Position(2, 3), new Position(2, 5),
                    new Position(3, 2), new Position(3, 6),
                    new Position(5, 2), new Position(5, 6),
                    new Position(6, 3), new Position(6, 5)
            );
        }

        @Test
        void testBlockedBySameColour() {
            // Place friendly pieces at some valid knight moves
            board.setPiece(new Position(2, 3), new Knight(Colour.WHITE));
            board.setPiece(new Position(6, 5), new Knight(Colour.WHITE));

            // The knight should be able to move everywhere except those squares
            verifyValidMoves(
                    new Position(2, 5),
                    new Position(3, 2), new Position(3, 6),
                    new Position(5, 2), new Position(5, 6)
            );
        }

        @Test
        void testCanCaptureOpponent() {
            // Place opponent pieces at some valid knight moves
            board.setPiece(new Position(2, 3), new Knight(Colour.BLACK));
            board.setPiece(new Position(6, 5), new Knight(Colour.BLACK));

            // The knight should be able to move normally, including capturing those pieces
            verifyValidMoves(
                    new Position(2, 3), new Position(2, 5),
                    new Position(3, 2), new Position(3, 6),
                    new Position(5, 2), new Position(5, 6),
                    new Position(6, 5)
            );
        }

        @Test
        void shouldNotBeBlockedByPiecesInPath() {
            // set up
            // surround the knight and make sure it can still move


            // exercise


            // verify

        }
    }

    @Nested
    class Black {

        @BeforeEach
        void setup() {
            underTest = new Knight(Colour.BLACK);
            KnightTest.this.setup(underTest);
            board.setPiece(startPosition, underTest);
        }

        @Test
        void testUnobstructedMovement() {
            // set up
            // surround knight and validate can still move
            board.setPiece(new Position(3, 3), new Knight(Colour.BLACK));
            board.setPiece(new Position(3, 4), new Knight(Colour.BLACK));
            board.setPiece(new Position(3, 5), new Knight(Colour.BLACK));
            board.setPiece(new Position(4, 3), new Knight(Colour.BLACK));
            board.setPiece(new Position(4, 5), new Knight(Colour.BLACK));
            board.setPiece(new Position(5, 3), new Knight(Colour.BLACK));
            board.setPiece(new Position(5, 4), new Knight(Colour.BLACK));
            board.setPiece(new Position(5, 5), new Knight(Colour.BLACK));

            // verify
            verifyValidMoves(
                    new Position(2, 3), new Position(2, 5),
                    new Position(3, 2), new Position(3, 6),
                    new Position(5, 2), new Position(5, 6),
                    new Position(6, 3), new Position(6, 5)
            );
        }

        @Test
        void testBlockedBySameColour() {
            board.setPiece(new Position(2, 3), new Knight(Colour.BLACK));
            board.setPiece(new Position(6, 5), new Knight(Colour.BLACK));

            verifyValidMoves(
                    new Position(2, 5),
                    new Position(3, 2), new Position(3, 6),
                    new Position(5, 2), new Position(5, 6),
                    new Position(6, 3)
            );
        }

        @Test
        void testCanCaptureOpponent() {
            board.setPiece(new Position(2, 3), new Knight(Colour.WHITE));
            board.setPiece(new Position(6, 5), new Knight(Colour.WHITE));

            verifyValidMoves(
                    new Position(2, 3), new Position(2, 5),
                    new Position(3, 2), new Position(3, 6),
                    new Position(5, 2), new Position(5, 6),
                    new Position(6, 3), new Position(6, 5)
            );
        }
    }
}