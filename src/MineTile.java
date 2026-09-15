import javax.swing.JButton;

public class MineTile extends JButton {

    private final int row;
    private final int col;

    public MineTile(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}