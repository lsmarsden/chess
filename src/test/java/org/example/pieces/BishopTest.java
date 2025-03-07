package org.example.pieces;

import org.example.Colour;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

class BishopTest extends PieceTestBase<Bishop> {

    private Bishop underTest = new Bishop(Colour.BLACK);

    @BeforeEach
    void setUp() {
        setup(underTest);
        startPosition = new Position(4, 4);
    }

    @Override
    protected String expectedName() {
        return "Bishop";
    }

    @Override
    protected char expectedSymbol() {
        return 'B';
    }

    @Nested
    class White {

        @BeforeEach
        void setup() {
            underTest = new Bishop(Colour.WHITE);
            BishopTest.this.setup(underTest);
            startPosition = new Position(4, 4);
            board.setPiece(startPosition, underTest);
        }

        @Test
        void testUnobstructedMovement() {
            // set up
            clearPositions(List.of(
                    new Position(0, 0),
                    new Position(3, 3), new Position(2, 2), new Position(1, 1),
                    new Position(5, 5), new Position(6, 6), new Position(7, 7),
                    new Position(3, 5), new Position(2, 6), new Position(1, 7),
                    new Position(5, 3), new Position(6, 2), new Position(7, 1)
            ));

            // exercise & verify
            verifyValidMoves(
                    new Position(0, 0),
                    new Position(3, 3), new Position(2, 2), new Position(1, 1),
                    new Position(5, 5), new Position(6, 6), new Position(7, 7),
                    new Position(3, 5), new Position(2, 6), new Position(1, 7),
                    new Position(5, 3), new Position(6, 2), new Position(7, 1)
            );
        }

        @Test
        void testBlockedBySameColour() {
            // set up
            board.setPiece(new Position(3, 3), new Bishop(Colour.WHITE));
            board.setPiece(new Position(5, 5), new Bishop(Colour.WHITE));

            // exercise & verify
            verifyValidMoves(List.of(
                    new Position(1, 7),
                    new Position(2, 6),
                    new Position(3, 5),
                    new Position(5, 3)
            ));
        }

        @Test
        void testCanCaptureOpponent() {
            // set up
            board.setPiece(new Position(3, 3), new Bishop(Colour.BLACK));
            board.setPiece(new Position(5, 5), new Bishop(Colour.BLACK));

            // exercise & verify
            verifyValidMoves(
                    new Position(1, 7),
                    new Position(2, 6),
                    new Position(3, 5),
                    new Position(3, 3),
                    new Position(5, 5),
                    new Position(5, 3));
        }
    }

    @Nested
    class Black {

        @BeforeEach
        void setup() {
            underTest = new Bishop(Colour.BLACK);
            BishopTest.this.setup(underTest);
            startPosition = new Position(4, 4);
            board.setPiece(startPosition, underTest);
        }

        @Test
        void testUnobstructedMovement() {
            // set up
            clearPositions(List.of(
                    new Position(3, 3), new Position(2, 2), new Position(1, 1),
                    new Position(5, 5), new Position(6, 6), new Position(7, 7),
                    new Position(3, 5), new Position(2, 6), new Position(1, 7),
                    new Position(5, 3), new Position(6, 2), new Position(7, 1)
            ));

            // exercise & verify
            verifyValidMoves(
                    new Position(3, 3), new Position(2, 2), new Position(1, 1),
                    new Position(5, 5), new Position(6, 6), new Position(7, 7),
                    new Position(3, 5), new Position(2, 6), new Position(1, 7),
                    new Position(5, 3), new Position(6, 2), new Position(7, 1)
            );
        }

        @Test
        void testBlockedBySameColour() {
            // set up
            board.setPiece(new Position(3, 3), new Bishop(Colour.BLACK));
            board.setPiece(new Position(5, 5), new Bishop(Colour.BLACK));

            // exercise & verify
            verifyValidMoves(List.of(
                    new Position(6, 2),
                    new Position(2, 6),
                    new Position(3, 5),
                    new Position(5, 3)
            ));
        }

        @Test
        void testCanCaptureOpponent() {
            // set up
            board.setPiece(new Position(3, 3), new Bishop(Colour.WHITE));
            board.setPiece(new Position(5, 5), new Bishop(Colour.WHITE));

            // exercise & verify
            verifyValidMoves(
                    new Position(6, 2),
                    new Position(2, 6),
                    new Position(3, 5),
                    new Position(3, 3),
                    new Position(5, 5),
                    new Position(5, 3));
        }
    }
}