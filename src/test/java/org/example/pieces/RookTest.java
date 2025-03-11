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

@Story("Rook")
class RookTest extends PieceTestBase<Rook> {

    private Rook underTest = new Rook(Colour.BLACK);

    @BeforeEach
    void setUp() {
        setup(underTest);
        startPosition = new Position(4, 4);
    }

    @Override
    protected String expectedName() {
        return "Rook";
    }

    @Override
    protected char expectedSymbol() {
        return 'R';
    }

    @Nested
    @Story("White Rook Movement")
    class White {

        @BeforeEach
        void setup() {
            underTest = new Rook(Colour.WHITE);
            RookTest.this.setup(underTest);
            startPosition = new Position(4, 4);
            board.setPiece(startPosition, underTest);
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the white bishop moves correctly when unobstructed")
        void testUnobstructedMovement() {
            // set up
            clearPositions(List.of(
                    new Position(0, 4), new Position(1, 4),
                    new Position(6, 4), new Position(7, 4)
            ));

            // exercise & verify
            verifyValidMoves(
                    new Position(4, 0), new Position(4, 1), new Position(4, 2), new Position(4, 3),
                    new Position(4, 5), new Position(4, 6), new Position(4, 7),
                    new Position(0, 4), new Position(1, 4), new Position(2, 4), new Position(3, 4),
                    new Position(5, 4), new Position(6, 4), new Position(7, 4)
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the white bishop is blocked by pieces of the same color")
        void testBlockedBySameColour() {
            // set up
            board.setPiece(new Position(4, 3), new Rook(Colour.WHITE));
            board.setPiece(new Position(4, 5), new Rook(Colour.WHITE));

            // exercise & verify
            verifyValidMoves(List.of(
                    new Position(3, 4), new Position(2, 4), new Position(1, 4),
                    new Position(5, 4)
            ));
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the white bishop can capture opponent pieces")
        void testCanCaptureOpponent() {
            // set up
            board.setPiece(new Position(4, 3), new Rook(Colour.BLACK));
            board.setPiece(new Position(4, 5), new Rook(Colour.BLACK));

            // exercise & verify
            verifyValidMoves(List.of(
                    new Position(3, 4), new Position(2, 4), new Position(1, 4),
                    new Position(5, 4), new Position(4, 3), new Position(4, 5)
            ));
        }
    }

    @Nested
    @Story("Black Rook Movement")
    class Black {

        @BeforeEach
        void setup() {
            underTest = new Rook(Colour.BLACK);
            RookTest.this.setup(underTest);
            startPosition = new Position(4, 4);
            board.setPiece(startPosition, underTest);
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the black bishop moves correctly when unobstructed")
        void testUnobstructedMovement() {
            // set up
            clearPositions(List.of(
                    new Position(0, 4), new Position(1, 4),
                    new Position(6, 4), new Position(7, 4)
            ));

            // exercise & verify
            verifyValidMoves(
                    new Position(4, 0), new Position(4, 1), new Position(4, 2), new Position(4, 3),
                    new Position(4, 5), new Position(4, 6), new Position(4, 7),
                    new Position(0, 4), new Position(1, 4), new Position(2, 4), new Position(3, 4),
                    new Position(5, 4), new Position(6, 4), new Position(7, 4)
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the black bishop is blocked by pieces of the same color")
        void testBlockedBySameColour() {
            // set up
            board.setPiece(new Position(4, 3), new Rook(Colour.BLACK));
            board.setPiece(new Position(4, 5), new Rook(Colour.BLACK));

            // exercise & verify
            verifyValidMoves(List.of(
                    new Position(3, 4), new Position(2, 4), new Position(6, 4),
                    new Position(5, 4)
            ));
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the black bishop can capture opponent pieces")
        void testCanCaptureOpponent() {
            // set up
            board.setPiece(new Position(4, 3), new Rook(Colour.WHITE));
            board.setPiece(new Position(4, 5), new Rook(Colour.WHITE));

            // exercise & verify
            verifyValidMoves(List.of(
                    new Position(3, 4), new Position(2, 4), new Position(6, 4),
                    new Position(5, 4), new Position(4, 3), new Position(4, 5)
            ));
        }
    }
}
