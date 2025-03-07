package org.example.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Game;
import org.example.Position;
import org.example.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChessGUI extends JPanel {
    private static final Logger logger = LogManager.getLogger(ChessGUI.class);
    private final int TILE_SIZE = 80; // Size of each tile
    private final Game game;
    private final Map<String, Image> pieceImages = new HashMap<>();
    private Position selected = null; // Track selected piece


    public ChessGUI(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(8 * TILE_SIZE, 8 * TILE_SIZE));
        loadPieceImages(); // Load images into memory

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = e.getX() / TILE_SIZE;
                int col = e.getY() / TILE_SIZE;
                Position clickedPos = new Position(col, row);

                logger.info("Clicked position: {}", clickedPos);
                logger.info("Current selected position: {}", selected);

                if (selected == null) {
                    Piece piece = game.getBoard().getPiece(clickedPos);
                    if (piece != null && piece.getColour() == game.getCurrentPlayer()) {
                        selected = clickedPos;
                        logger.info("Selected piece at: {}", selected);
                    }
                } else {
                    // Reset selection after a move attempt
                    if (selected.equals(clickedPos)) {
                        // Clicked the same square -> Deselect
                        logger.info("Deselected piece at: {}", selected);
                    } else {
                        // Attempt to move
                        if (game.makeMove(selected, clickedPos)) {
                            logger.info("Moved from {} to {}", selected, clickedPos);
                        } else {
                            logger.info("Invalid move: {}, {}", selected, clickedPos);
                        }
                    }
                    selected = null;
                }
                repaint();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chess");
            Game game = new Game();
            ChessGUI chessGUI = new ChessGUI(game);

            frame.add(chessGUI);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    private void loadPieceImages() {
        String[] pieces = {"wP", "wR", "wN", "wB", "wQ", "wK", "bP", "bR", "bN", "bB", "bQ", "bK"};
        for (String piece : pieces) {
            pieceImages.put(piece, new ImageIcon(Objects.requireNonNull(getClass().getResource("/pieces/" + piece + ".png"))).getImage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw Board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boolean isLight = (row + col) % 2 == 0;
                g.setColor(isLight ? Color.LIGHT_GRAY : Color.DARK_GRAY);
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        // Highlight selected square
        if (selected != null) {
            g.setColor(Color.YELLOW);
            g.fillRect(selected.getY() * TILE_SIZE, selected.getX() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        // Redraw pieces
        // Draw pieces
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = game.getBoard().getPiece(new Position(row, col));
                if (piece != null) {
                    String key = String.valueOf(piece.getColour().getSymbol()) + piece.getSymbol();
                    Image pieceImage = pieceImages.get(key);
                    if (pieceImage != null) {
                        g.drawImage(pieceImage, col * TILE_SIZE + 10, row * TILE_SIZE + 10, 60, 60, this);
                    }
                }
            }
        }
    }
}
