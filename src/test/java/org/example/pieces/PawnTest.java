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

import java.util.Collections;

@Story("Pawn")
class PawnTest extends PieceTestBase<Pawn> {

    private Pawn underTest = new Pawn(Colour.BLACK);

    @BeforeEach
    void setUp() {
        super.setup(underTest);
        startPosition = new Position(2, 3);
    }

    @Override
    protected String expectedName() {
        return "Pawn";
    }

    @Override
    protected char expectedSymbol() {
        return 'P';
    }

    @Nested
    @Story("White Pawn Movement")
    class White {

        @BeforeEach
        void setup() {
            underTest = new Pawn(Colour.WHITE);
            PawnTest.this.setup(underTest);
            startPosition = new Position(6, 4);
            board.setPiece(startPosition, underTest);
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("A white pawn can move one or two squares forward on its first move if no obstacles exist.")
        void testFirstTurn_noObstacles_canMoveTwoForward() {
            // set up
            underTest.setHasMoved(false);
            board.setPiece(new Position(5, 4), null);
            board.setPiece(new Position(4, 4), null);

            // exercise & verify
            verifyValidMoves(
                    new Position(5, 4), // Move one square forward
                    new Position(4, 4)  // Move two squares forward
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("A white pawn cannot move forward if a piece is blocking it on its first move.")
        void testFirstTurn_blocked_cannotMove() {
            // set up
            underTest.setHasMoved(false);
            board.setPiece(new Position(5, 4), new Pawn(Colour.BLACK)); // Block directly in front

            // exercise & verify
            verifyValidMoves(Collections.emptyList());
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("A white pawn can move forward one square if only the second square ahead is blocked.")
        void testFirstTurn_pieceTwoAheadButNotOneAhead_canMoveOne() {
            // set up
            underTest.setHasMoved(false);
            board.setPiece(new Position(4, 4), new Pawn(Colour.BLACK)); // Block 2 squares ahead

            // exercise & verify
            verifyValidMoves(
                    new Position(5, 4) // Can move one square forward, but not two
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("After the first move, a white pawn can only move forward one square if no obstacles exist.")
        void testSecondTurn_noObstacles_canMoveOneForwardOnly() {
            // given
            underTest.setHasMoved(true); // Simulate that the pawn has already moved
            board.setPiece(new Position(4, 4), null);
            board.setPiece(new Position(5, 4), null);

            // then
            verifyValidMoves(
                    new Position(5, 4) // Can move one square forward only
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("A white pawn cannot move if a piece is directly in front of it after its first move.")
        void testSecondTurn_blocked_cannotMove() {
            // given
            underTest.setHasMoved(true);
            board.setPiece(new Position(5, 4), new Pawn(Colour.BLACK)); // Block directly in front

            // then
            verifyValidMoves(Collections.emptyList());
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("A white pawn can capture an opponent’s piece diagonally.")
        void testCanCaptureDiagonally_opponentPresent() {
            // given
            underTest.setHasMoved(true);
            board.setPiece(new Position(5, 3), new Pawn(Colour.BLACK)); // Opponent left diagonal
            board.setPiece(new Position(5, 5), new Pawn(Colour.BLACK)); // Opponent right diagonal

            // then
            verifyValidMoves(
                    new Position(5, 3), // Move one forward
                    new Position(5, 4), // Capture left
                    new Position(5, 5)  // Capture right
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("A white pawn cannot capture diagonally if the space is empty or occupied by an allied piece.")
        void testCannotCaptureDiagonally_emptyOrSameColor() {
            // given
            board.setPiece(new Position(5, 2), new Pawn(Colour.WHITE)); // Same color on left diagonal
            board.setPiece(new Position(5, 4), null); // Empty on right diagonal

            // then
            verifyValidMoves(
                    new Position(5, 4),
                    new Position(4, 4)
            );
        }
    }

    @Nested
    @Story("Black Pawn Movement")
    class Black {

        @BeforeEach
        void setup() {
            underTest = new Pawn(Colour.BLACK);
            PawnTest.this.setup(underTest);
            startPosition = new Position(1, 4);
            board.setPiece(startPosition, underTest);
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("A black pawn can move one or two squares forward on its first move if no obstacles exist.")
        void testFirstTurn_noObstacles_canMoveTwoForward() {
            // set up
            underTest.setHasMoved(false);
            board.setPiece(new Position(2, 4), null);
            board.setPiece(new Position(3, 4), null);

            // exercise & verify
            verifyValidMoves(
                    new Position(2, 4), // Move one square forward
                    new Position(3, 4)  // Move two squares forward
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("A black pawn cannot move forward if a piece is blocking it on its first move.")
        void testFirstTurn_blocked_cannotMove() {
            // set up
            underTest.setHasMoved(false);
            board.setPiece(new Position(2, 4), new Pawn(Colour.BLACK)); // Block directly in front

            // exercise & verify
            verifyValidMoves(Collections.emptyList());
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("A black pawn can move forward one square if only the second square ahead is blocked.")
        void testFirstTurn_pieceTwoAheadButNotOneAhead_canMoveOne() {
            // set up
            underTest.setHasMoved(false);
            board.setPiece(new Position(3, 4), new Pawn(Colour.BLACK)); // Block 2 squares ahead

            // exercise & verify
            verifyValidMoves(
                    new Position(2, 4) // Can move one square forward, but not two
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("After the first move, a black pawn can only move forward one square if no obstacles exist.")
        void testSecondTurn_noObstacles_canMoveOneForwardOnly() {
            // given
            underTest.setHasMoved(true); // Simulate that the pawn has already moved
            board.setPiece(new Position(2, 4), null);
            board.setPiece(new Position(3, 4), null);

            // then
            verifyValidMoves(
                    new Position(2, 4) // Can move one square forward only
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("A black pawn cannot move if a piece is directly in front of it after its first move.")
        void testSecondTurn_blocked_cannotMove() {
            // given
            underTest.setHasMoved(true);
            board.setPiece(new Position(2, 4), new Pawn(Colour.BLACK)); // Block directly in front

            // then
            verifyValidMoves(Collections.emptyList());
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("A black pawn can capture an opponent’s piece diagonally.")
        void testCanCaptureDiagonally_opponentPresent() {
            // given
            underTest.setHasMoved(true);
            board.setPiece(new Position(2, 3), new Pawn(Colour.WHITE)); // Opponent left diagonal
            board.setPiece(new Position(2, 5), new Pawn(Colour.WHITE)); // Opponent right diagonal

            // then
            verifyValidMoves(
                    new Position(2, 3), // Move one forward
                    new Position(2, 4), // Capture left
                    new Position(2, 5)  // Capture right
            );
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Description("A black pawn cannot capture diagonally if the space is empty or occupied by an allied piece.")
        void testCannotCaptureDiagonally_emptyOrSameColor() {
            // given
            board.setPiece(new Position(2, 2), new Pawn(Colour.BLACK)); // Same color on left diagonal
            board.setPiece(new Position(2, 4), null); // Empty on right diagonal

            // then
            verifyValidMoves(
                    new Position(2, 4),
                    new Position(3, 4)
            );
        }
    }
}