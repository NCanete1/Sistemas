import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class Panel extends JPanel {
    private int row = 100;
    private int column = 100;
    private static final int GAP = 1;
    private static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 1);
    private JLabel[][] grid = new JLabel[row][column];

    public Panel() {
        JPanel Ppanel = new JPanel(new GridLayout(row, column, GAP, GAP));
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new JLabel("     ", SwingConstants.CENTER);
                grid[row][col].setFont(LABEL_FONT); // make it big
                grid[row][col].setOpaque(true);
                grid[row][col].setBackground(Color.WHITE);
                Ppanel.add(grid[row][col]);
            }
        }

        setLayout(new BorderLayout());
        add(Ppanel, BorderLayout.CENTER);
    }
}
