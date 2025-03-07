package org.example;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

@Getter
public class Game {

    private static final Logger logger = LogManager.getLogger(Game.class);
    private final Board board = new Board();
    private final Scanner scanner = new Scanner(System.in);
    private Colour currentPlayer = Colour.WHITE;
    private boolean gameOver = false;

    public boolean makeMove(Position from, Position to) {
        if (gameOver) return false; // Prevent moves after game over

        Move move = new Move(currentPlayer, from, to);
        logger.info("Processing move: {}", move);

        if (!board.isValidMove(move)) {
            logger.warn("Invalid move: {}", move);
            return false;
        }
        MoveResult moveResult = board.move(move);

        // if current player put themselves in check, revert the move
        if (board.isCheck(currentPlayer)) {
            logger.warn("Move puts player in check: {}", move);
            board.undoMove(moveResult);
            return false;
        }

        switchPlayer();
        checkGameState();
        return true;
    }

    private void checkGameState() {
        logger.info("Checking game state");
        if (board.isCheckmate(currentPlayer)) {
            System.out.println(currentPlayer + "loses");
            gameOver = true;
        } else if (board.isCheck(currentPlayer)) {
            System.out.println(currentPlayer + " is in check");
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
        logger.info("{} to move", currentPlayer);
    }
}