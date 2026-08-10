import java.awt.*;
import javax.swing.*;

public class Interface {
    JFrame frame;
    JLabel textLabel;
    JPanel boardPanel;

    public Interface() {
        frame = new JFrame("TNT");
        textLabel = new JLabel();
        boardPanel = new JPanel();
        frame.setSize(Config.COLS * Config.TILE_SIZE, Config.ROWS * Config.TILE_SIZE);

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.BOLD, 20));
        textLabel.setHorizontalAlignment(JLabel.CENTER);

        textLabel.setOpaque(true);
        textLabel.setText("Difficulty: " + Config.MINES);
        
        frame.add(textLabel, BorderLayout.NORTH);
        boardPanel.setLayout(new GridLayout(Config.ROWS,Config.COLS));
        frame.add(boardPanel);
    }

    public void addTile(MineTile tile) {
        boardPanel.add(tile);
    }

    public void setText(String text) {
        textLabel.setText(text);
    }

    public void show() {
        frame.setVisible(true);
    }
}