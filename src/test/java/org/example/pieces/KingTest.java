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

@Story("King")
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
    @Story("White King Movement")
    class White {

        @BeforeEach
        void setup() {
            underTest = new King(Colour.WHITE);
            KingTest.this.setup(underTest);
            startPosition = new Position(4, 4);
            board.setPiece(startPosition, underTest);
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the white king moves correctly when unobstructed")
        void testUnobstructedMovement() {
            // exercise & verify
            verifyValidMoves(
                    new Position(3, 3), new Position(3, 4), new Position(3, 5),
                    new Position(4, 3), new Position(4, 5),
                    new Position(5, 3), new Position(5, 4), new Position(5, 5)
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the white king is blocked by pieces of the same color")
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
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the white king can capture opponent pieces")
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
    @Story("Black King Movement")
    class Black {

        @BeforeEach
        void setup() {
            underTest = new King(Colour.BLACK);
            KingTest.this.setup(underTest);
            startPosition = new Position(4, 4);
            board.setPiece(startPosition, underTest);
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the black king moves correctly when unobstructed")
        void testUnobstructedMovement() {
            // exercise & verify
            verifyValidMoves(
                    new Position(3, 3), new Position(3, 4), new Position(3, 5),
                    new Position(4, 3), new Position(4, 5),
                    new Position(5, 3), new Position(5, 4), new Position(5, 5)
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the black king is blocked by pieces of the same color")
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
        @Severity(SeverityLevel.CRITICAL)
        @Description("Ensures the black king can capture opponent pieces")
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