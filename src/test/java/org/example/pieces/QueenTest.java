package org.example.pieces;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.Colour;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

@Story("Queen")
class QueenTest extends PieceTestBase<Queen> {

    private Queen underTest = new Queen(Colour.BLACK);

    @BeforeEach
    void setUp() {
        super.setup(underTest);
        startPosition = new Position(4, 4);
    }


    @Override
    protected String expectedName() {
        return "Queen";
    }

    @Override
    protected char expectedSymbol() {
        return 'Q';
    }

    @Nested
    @Story("White Queen Movement")
    class White {

        @BeforeEach
        void setup() {
            underTest = new Queen(Colour.WHITE);
            QueenTest.this.setup(underTest);
            startPosition = new Position(4, 4);
            board.setPiece(startPosition, underTest);
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the white queen moves correctly when unobstructed")
        void testUnobstructedMovement() {
            // set up
            clearPositions(List.of(
                    new Position(0, 0),
                    new Position(3, 3), new Position(2, 2), new Position(1, 1),
                    new Position(5, 5), new Position(6, 6), new Position(7, 7),
                    new Position(3, 5), new Position(2, 6), new Position(1, 7),
                    new Position(5, 3), new Position(6, 2), new Position(7, 1),
                    new Position(0, 4), new Position(1, 4),
                    new Position(6, 4), new Position(7, 4)
            ));

            // exercise & verify
            verifyValidMoves(
                    new Position(0, 0),
                    new Position(3, 3), new Position(2, 2), new Position(1, 1),
                    new Position(5, 5), new Position(6, 6), new Position(7, 7),
                    new Position(3, 5), new Position(2, 6), new Position(1, 7),
                    new Position(5, 3), new Position(6, 2), new Position(7, 1),
                    new Position(4, 0), new Position(4, 1), new Position(4, 2), new Position(4, 3),
                    new Position(4, 5), new Position(4, 6), new Position(4, 7),
                    new Position(0, 4), new Position(1, 4), new Position(2, 4), new Position(3, 4),
                    new Position(5, 4), new Position(6, 4), new Position(7, 4)
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the white queen is blocked by pieces of the same color")
        void testBlockedBySameColour() {
            // set up
            board.setPiece(new Position(3, 3), new Bishop(Colour.WHITE));
            board.setPiece(new Position(5, 5), new Bishop(Colour.WHITE));
            board.setPiece(new Position(4, 3), new Rook(Colour.WHITE));
            board.setPiece(new Position(4, 5), new Rook(Colour.WHITE));

            // exercise & verify
            verifyValidMoves(List.of(
                    new Position(1, 7),
                    new Position(2, 6),
                    new Position(3, 5),
                    new Position(5, 3),
                    new Position(3, 4),
                    new Position(2, 4),
                    new Position(1, 4),
                    new Position(5, 4)
            ));
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the white queen can capture opponent pieces")
        void testCanCaptureOpponent() {
            // set up
            board.setPiece(new Position(3, 3), new Bishop(Colour.BLACK));
            board.setPiece(new Position(5, 5), new Bishop(Colour.BLACK));
            board.setPiece(new Position(4, 3), new Rook(Colour.BLACK));
            board.setPiece(new Position(4, 5), new Rook(Colour.BLACK));

            // exercise & verify
            verifyValidMoves(
                    new Position(1, 7),
                    new Position(2, 6),
                    new Position(3, 5),
                    new Position(3, 3),
                    new Position(5, 5),
                    new Position(5, 3),
                    new Position(3, 4),
                    new Position(2, 4),
                    new Position(1, 4),
                    new Position(5, 4),
                    new Position(4, 3),
                    new Position(4, 5)
            );
        }
    }

    @Nested
    @Story("Black Queen Movement")
    class Black {

        @BeforeEach
        void setup() {
            underTest = new Queen(Colour.BLACK);
            QueenTest.this.setup(underTest);
            startPosition = new Position(4, 4);
            board.setPiece(startPosition, underTest);
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the black queen moves correctly when unobstructed")
        void testUnobstructedMovement() {
            // set up
            clearPositions(List.of(
                    new Position(0, 0),
                    new Position(3, 3), new Position(2, 2), new Position(1, 1),
                    new Position(5, 5), new Position(6, 6), new Position(7, 7),
                    new Position(3, 5), new Position(2, 6), new Position(1, 7),
                    new Position(5, 3), new Position(6, 2), new Position(7, 1),
                    new Position(0, 4), new Position(1, 4),
                    new Position(6, 4), new Position(7, 4)
            ));

            // exercise & verify
            verifyValidMoves(
                    new Position(0, 0),
                    new Position(3, 3), new Position(2, 2), new Position(1, 1),
                    new Position(5, 5), new Position(6, 6), new Position(7, 7),
                    new Position(3, 5), new Position(2, 6), new Position(1, 7),
                    new Position(5, 3), new Position(6, 2), new Position(7, 1),
                    new Position(4, 0), new Position(4, 1), new Position(4, 2), new Position(4, 3),
                    new Position(4, 5), new Position(4, 6), new Position(4, 7),
                    new Position(0, 4), new Position(1, 4), new Position(2, 4), new Position(3, 4),
                    new Position(5, 4), new Position(6, 4), new Position(7, 4)
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the black queen is blocked by pieces of the same color")
        void testBlockedBySameColour() {
            // set up
            board.setPiece(new Position(3, 3), new Bishop(Colour.BLACK));
            board.setPiece(new Position(5, 5), new Bishop(Colour.BLACK));
            board.setPiece(new Position(4, 3), new Rook(Colour.BLACK));
            board.setPiece(new Position(4, 5), new Rook(Colour.BLACK));

            // exercise & verify
            verifyValidMoves(List.of(
                    new Position(6, 2),
                    new Position(2, 6),
                    new Position(3, 5),
                    new Position(5, 3),
                    new Position(3, 4),
                    new Position(2, 4),
                    new Position(6, 4),
                    new Position(5, 4)
            ));
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the black queen can capture opponent pieces")
        void testCanCaptureOpponent() {
            // set up
            board.setPiece(new Position(3, 3), new Bishop(Colour.WHITE));
            board.setPiece(new Position(5, 5), new Bishop(Colour.WHITE));
            board.setPiece(new Position(4, 3), new Rook(Colour.WHITE));
            board.setPiece(new Position(4, 5), new Rook(Colour.WHITE));

            // exercise & verify
            verifyValidMoves(
                    new Position(6, 2),
                    new Position(2, 6),
                    new Position(3, 5),
                    new Position(3, 3),
                    new Position(5, 5),
                    new Position(5, 3),
                    new Position(3, 4),
                    new Position(2, 4),
                    new Position(6, 4),
                    new Position(5, 4),
                    new Position(4, 3),
                    new Position(4, 5)
            );
        }
    }
}