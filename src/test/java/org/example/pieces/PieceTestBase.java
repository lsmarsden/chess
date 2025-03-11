package org.example.pieces;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.example.Board;
import org.example.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Chess Game")
@Feature("Piece Movement")
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
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that the piece name matches the expected name.")
    void getName() {
        // exercise & verify
        assertThat(underTest.getName()).isEqualTo(expectedName());
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that the piece symbol matches the expected character.")
    void getSymbol() {
        // exercise & verify
        assertThat(underTest.getSymbol()).isEqualTo(expectedSymbol());
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Validates whether the piece has the ability to jump over others.")
    void canJump() {
        // exercise & verify
        assertThat(underTest.canJump()).isEqualTo(shouldBeAbleToJump());
    }

    protected boolean shouldBeAbleToJump() {
        return false;
    }

    protected abstract String expectedName();

    protected abstract char expectedSymbol();

    @Step("Clear positions on the board")
    protected void clearPositions(List<Position> positions) {
        for (Position position : positions) {
            board.setPiece(position, null);
        }
    }

    @Step("Verify valid moves for {this.underTest} at {this.startPosition}")
    protected void verifyValidMoves(List<Position> validMoves) {
        assertThat(getValidMoves()).describedAs("Board state: \n" + board)
                .containsExactlyInAnyOrderElementsOf(validMoves);
    }

    @Step("Verify valid moves for {this.underTest} at {this.startPosition}")
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