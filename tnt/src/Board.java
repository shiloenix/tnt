public class Board {
    private final MineTile[][] board;

    public Board() {
        board = new MineTile[Config.ROWS][Config.COLS];
        for (int r = 0; r < Config.ROWS; r++) {
            for (int c = 0; c < Config.COLS; c++) {
                board[r][c] = new MineTile(r, c);
            }
        }
    }

    public MineTile[][] getBoard() {
        return board;
    }
}