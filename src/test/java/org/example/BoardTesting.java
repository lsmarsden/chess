package org.example;

import java.util.List;

public class BoardTesting {
    public static void clearPositions(Board board, List<Position> positions) {
        for (Position position : positions) {
            board.setPiece(position, null);
        }
    }
}
