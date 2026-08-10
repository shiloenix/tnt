import java.awt.*;
import java.awt.event.*;

public class Tnt {

    private final Interface ui;
    private final Board board;
    private final Logic logic;

    public Tnt() {
        ui = new Interface();
        board = new Board();
        logic = new Logic(
            board.getBoard()
        );
        createBoard();
        ui.show();
    }

    private void createBoard() {
        for (int r = 0; r < Config.ROWS; r++) {
            for (int c = 0; c < Config.COLS; c++) {
                MineTile tile = board.getBoard()[r][c];
                styleTile(tile);
                addListener(tile);
                ui.addTile(tile);
            }
        }
    }

    private void styleTile(MineTile tile) {
        tile.setFocusable(false);
        tile.setMargin(new Insets(0, 0, 0, 0));
        tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        tile.setText("");
    }

    private void addListener(MineTile tile) {
        tile.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (logic.isGameOver() || logic.isGameWon()) {
                    return;
                }
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (tile.getText().equals("")) {
                        if (logic.isMine(tile)) {
                            logic.revealMines();
                            ui.setText("Game Over!");
                        } else {
                            logic.checkMines(tile.getRow(), tile.getCol());
                            if (logic.isGameWon()) {
                                ui.setText("You Win!");
                            }
                        }
                    }

                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    if (tile.getText().equals("") && tile.isEnabled()) {
                        tile.setText("⚑");
                    } else if (tile.getText().equals("⚑")) {
                        tile.setText("");
                    }
                }
            }
        });
    }
}