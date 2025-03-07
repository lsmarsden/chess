package org.example.pieces;

import org.example.Board;
import org.example.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class PieceTestBase<T extends Piece> {

    protected T underTest;

    protected Board board = new Board();
    protected Position startPosition;

    protected void setup(T underTest) {
        this.underTest = underTest;
    }

    private Position getFrom() {
        return startPosition;
    }

    @Test
    void getName() {
        // exercise & verify
        assertThat(underTest.getName()).isEqualTo(expectedName());
    }

    @Test
    void getSymbol() {
        // exercise & verify
        assertThat(underTest.getSymbol()).isEqualTo(expectedSymbol());
    }

    @Test
    void canJump() {
        // exercise & verify
        assertThat(underTest.canJump()).isEqualTo(shouldBeAbleToJump());
    }

    protected boolean shouldBeAbleToJump() {
        return false;
    }

    protected abstract String expectedName();

    protected abstract char expectedSymbol();

    protected void clearPositions(List<Position> positions) {
        for (Position position : positions) {
            board.setPiece(position, null);
        }
    }

    protected void verifyValidMoves(List<Position> validMoves) {
        assertThat(getValidMoves()).describedAs("Board state: \n" + board)
                .containsExactlyInAnyOrderElementsOf(validMoves);
    }

    protected void verifyValidMoves(Position... validMoves) {
        assertThat(getValidMoves()).describedAs("Board state: \n" + board)
                .containsExactlyInAnyOrder(validMoves);
    }

    private List<Position> getValidMoves() {
        List<Position> moves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position to = new Position(i, j);

                if (underTest.canMove(getFrom(), to, board)) {
                    moves.add(to);
                }
            }
        }
        return moves;
    }
}