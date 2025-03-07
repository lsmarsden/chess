package org.example.pieces;

import org.example.Colour;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

class KingTest extends PieceTestBase<King> {

    private King underTest = new King(Colour.WHITE);

    @BeforeEach
    void setUp() {
        super.setup(underTest);
    }

    @Override
    protected String expectedName() {
        return "King";
    }
    @Override
    protected char expectedSymbol() {
        return 'K';
    }
    @Nested
    class White {

        @BeforeEach
        void setup() {
            underTest = new King(Colour.WHITE);
            KingTest.this.setup(underTest);
            startPosition = new Position(4, 4);
            board.setPiece(startPosition, underTest);
        }

        @Test
        void testUnobstructedMovement() {
            // exercise & verify
            verifyValidMoves(
                    new Position(3, 3), new Position(3, 4), new Position(3, 5),
                    new Position(4, 3), new Position(4, 5),
                    new Position(5, 3), new Position(5, 4), new Position(5, 5)
            );
        }

        @Test
        void testBlockedBySameColour() {
            // set up
            board.setPiece(new Position(3, 3), new Knight(Colour.WHITE));
            board.setPiece(new Position(5, 5), new Knight(Colour.WHITE));

            // exercise & verify
            verifyValidMoves(List.of(
                    new Position(3, 4), new Position(3, 5),
                    new Position(4, 3), new Position(4, 5),
                    new Position(5, 3), new Position(5, 4)
            ));
        }

        @Test
        void testCanCaptureOpponent() {
            // set up
            board.setPiece(new Position(3, 3), new Knight(Colour.BLACK));
            board.setPiece(new Position(5, 5), new Knight(Colour.BLACK));

            // exercise & verify
            verifyValidMoves(
                    new Position(3, 3), new Position(3, 4), new Position(3, 5),
                    new Position(4, 3), new Position(4, 5),
                    new Position(5, 3), new Position(5, 4), new Position(5, 5)
            );
        }
    }

    @Nested
    class Black {

        @BeforeEach
        void setup() {
            underTest = new King(Colour.BLACK);
            KingTest.this.setup(underTest);
            startPosition = new Position(4, 4);
            board.setPiece(startPosition, underTest);
        }

        @Test
        void testUnobstructedMovement() {
            // exercise & verify
            verifyValidMoves(
                    new Position(3, 3), new Position(3, 4), new Position(3, 5),
                    new Position(4, 3), new Position(4, 5),
                    new Position(5, 3), new Position(5, 4), new Position(5, 5)
            );
        }

        @Test
        void testBlockedBySameColour() {
            // set up
            board.setPiece(new Position(3, 3), new Knight(Colour.BLACK));
            board.setPiece(new Position(5, 5), new Knight(Colour.BLACK));

            // exercise & verify
            verifyValidMoves(List.of(
                    new Position(3, 4), new Position(3, 5),
                    new Position(4, 3), new Position(4, 5),
                    new Position(5, 3), new Position(5, 4)
            ));
        }

        @Test
        void testCanCaptureOpponent() {
            // set up
            board.setPiece(new Position(3, 3), new Knight(Colour.WHITE));
            board.setPiece(new Position(5, 5), new Knight(Colour.WHITE));

            // exercise & verify
            verifyValidMoves(
                    new Position(3, 3), new Position(3, 4), new Position(3, 5),
                    new Position(4, 3), new Position(4, 5),
                    new Position(5, 3), new Position(5, 4), new Position(5, 5)
            );
        }
    }
}