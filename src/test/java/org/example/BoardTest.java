package org.example;

import org.example.pieces.Bishop;
import org.example.pieces.Knight;
import org.example.pieces.Pawn;
import org.example.pieces.Queen;
import org.example.pieces.Rook;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

    private Board underTest = new Board();

    @Test
    void initialSetup() {
        // exercise & verify
        assertThat(underTest.toString()).isEqualTo("""
                0 R N B Q K B N R\s
                1 P P P P P P P P\s
                2 - - - - - - - -\s
                3 - - - - - - - -\s
                4 - - - - - - - -\s
                5 - - - - - - - -\s
                6 P P P P P P P P\s
                7 R N B Q K B N R\s
                  0 1 2 3 4 5 6 7\s""");
    }

    @Test
    void blackShouldNotBeAbleToMoveWhitePiece() {
        // set up
        // black attempt to move white's pawn
        Move move = new Move(Colour.BLACK, new Position(6, 0), new Position(2, 0));

        // exercise & verify
        assertThat(underTest.isValidMove(move)).isFalse();
    }

    @Test
    void whiteShouldNotBeAbleToMoveBlackPiece() {
        // set up
        // white attempt to move black's pawn
        Move move = new Move(Colour.WHITE, new Position(1, 0), new Position(6, 0));

        // exercise & verify
        assertThat(underTest.isValidMove(move)).isFalse();
    }

    @Test
    void shouldNotBeAbleToMovePiecesOntoOwnPieces() {
        // set up
        // attempt to move white's rook onto its own pawn
        Move move = new Move(Colour.WHITE, new Position(0, 0), new Position(1, 0));

        // exercise & verify
        assertThat(underTest.isValidMove(move)).isFalse();
    }

    @Test
    void shouldNotMoveIfNoPieceAtFromPosition() {
        // set up
        Move move = new Move(Colour.WHITE, new Position(4, 4), new Position(1, 0));

        // exercise & verify
        assertThat(underTest.isValidMove(move)).isFalse();
    }

    @Nested
    class IsPathClear {
        @Test
        void testIsPathClear_horizontalRight_noObstacles() {
            Position from = new Position(3, 2);
            Position to = new Position(3, 5);
            assertTrue(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_horizontalRight_withObstacle() {
            Position from = new Position(3, 2);
            Position to = new Position(3, 5);
            underTest.setPiece(new Position(3, 3), new Rook(Colour.WHITE));
            assertFalse(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_horizontalLeft_noObstacles() {
            Position from = new Position(4, 6);
            Position to = new Position(4, 3);
            assertTrue(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_horizontalLeft_withObstacle() {
            Position from = new Position(4, 6);
            Position to = new Position(4, 3);
            underTest.setPiece(new Position(4, 4), new Rook(Colour.BLACK));
            assertFalse(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_verticalUp_noObstacles() {
            Position from = new Position(6, 4);
            Position to = new Position(2, 4);
            assertTrue(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_verticalUp_withObstacle() {
            Position from = new Position(6, 4);
            Position to = new Position(2, 4);
            underTest.setPiece(new Position(4, 4), new Pawn(Colour.WHITE));
            assertFalse(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_verticalDown_noObstacles() {
            Position from = new Position(1, 5);
            Position to = new Position(4, 5);
            assertTrue(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_verticalDown_withObstacle() {
            Position from = new Position(1, 5);
            Position to = new Position(4, 5);
            underTest.setPiece(new Position(3, 5), new Bishop(Colour.BLACK));
            assertFalse(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_diagonalUpRight_noObstacles() {
            // given
            BoardTesting.clearPositions(underTest, List.of(
                    new Position(0, 0),
                    new Position(1, 1),
                    new Position(6, 6),
                    new Position(7, 7)
            ));
            Position from = new Position(0, 0);
            Position to = new Position(7, 7);
            assertTrue(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_diagonalUpRight_withObstacle() {
            Position from = new Position(5, 2);
            Position to = new Position(2, 5);
            underTest.setPiece(new Position(4, 3), new Queen(Colour.WHITE));
            assertFalse(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_diagonalUpLeft_noObstacles() {
            Position from = new Position(5, 5);
            Position to = new Position(2, 2);
            assertTrue(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_diagonalUpLeft_withObstacle() {
            Position from = new Position(5, 5);
            Position to = new Position(2, 2);
            underTest.setPiece(new Position(3, 3), new Knight(Colour.BLACK));
            assertFalse(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_diagonalDownRight_noObstacles() {
            Position from = new Position(1, 1);
            Position to = new Position(4, 4);
            assertTrue(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_diagonalDownRight_withObstacle() {
            Position from = new Position(1, 1);
            Position to = new Position(4, 4);
            underTest.setPiece(new Position(3, 3), new Pawn(Colour.WHITE));
            assertFalse(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_diagonalDownLeft_noObstacles() {
            Position from = new Position(1, 4);
            Position to = new Position(4, 1);
            assertTrue(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_diagonalDownLeft_withObstacle() {
            Position from = new Position(1, 4);
            Position to = new Position(4, 1);
            underTest.setPiece(new Position(2, 3), new Rook(Colour.BLACK));
            assertFalse(underTest.isPathClear(from, to));
        }

        @Test
        void testIsPathClear_oneSquareMove_withPieces() {
            Position from = new Position(2, 2);
            Position to = new Position(2, 3);
            underTest.setPiece(from, new Rook(Colour.WHITE));
            underTest.setPiece(to, new Pawn(Colour.BLACK)); // Piece is at destination
            assertTrue(underTest.isPathClear(from, to)); // Path doesn't check from/to positions
        }

        @Test
        void testIsPathClear_notStraightLine() {
            // set up
            Position from = new Position(2, 2);
            Position to = new Position(5, 3);

            // exercise & verify
            assertThat(underTest.isPathClear(from, to)).isFalse();
        }
    }
}