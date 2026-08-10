import java.util.ArrayList;
import java.util.Random;


public class Logic {
    private final MineTile[][] board;
    private final ArrayList<MineTile> mineList;
    private final Random random;
    private int tilesClicked = 0;
    private boolean gameOver = false;
    private boolean gameWon = false;
    private boolean restartGame = false;

    private final int[][] directions = {
            {-1,-1}, {-1,0}, {-1,1},
            {0,-1}, {0,1},
            {1,-1}, {1,0}, {1,1}
    };

    public Logic(MineTile[][] board) {
        this.board = board;
        this.mineList = new ArrayList<>();
        this.random = new Random();
        setMines();
    }

    private void setMines() {
        int mineLeft = Config.MINES;
        while (mineLeft > 0) {
            int r = random.nextInt(Config.ROWS);
            int c = random.nextInt(Config.COLS);
            MineTile tile = board[r][c];

            if (!mineList.contains(tile)) {
                mineList.add(tile);
                mineLeft--;
            }
        }
    }

    public boolean isMine(MineTile tile) {
        return mineList.contains(tile);
    }

    public void revealMines() {
        for (MineTile tile : mineList) {
            tile.setText("☠");
        }
        gameOver = true;
    }

    public void checkMines(int r, int c) {
        if (r < 0 || r >= Config.ROWS || c < 0 || c >= Config.COLS) {
            return;
        }

        MineTile tile = board[r][c];

        if (!tile.isEnabled()) {
            return;
        }

        tile.setEnabled(false);
        tilesClicked++;
        int minesFound = 0;

        for (int[] d : directions) {
            minesFound += countMine(r + d[0], c + d[1]);
        }

        if (minesFound > 0) {
            tile.setText(Integer.toString(minesFound));
        } else {
            tile.setText("");
            for (int[] d : directions) {
                checkMines(r + d[0], c + d[1]);
            }
        }

        if (tilesClicked == Config.ROWS * Config.COLS - mineList.size()) {
            gameWon = true;
        }
    }

    private int countMine(int r, int c) {
        if (r < 0 || r >= Config.ROWS || c < 0 || c >= Config.COLS) {
            return 0;
        }

        if (mineList.contains(board[r][c])) {
            return 1;
        }

        return 0;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isRestarted(){
        return restartGame;
    }
}