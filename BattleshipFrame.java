import java.awt.*;
import javax.swing.*;


public class BattleshipFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 1020;
    private static final int DEFAULT_HEIGHT = 500;
    private JLabel[][] squaresLabelMy = new JLabel[10][10];
    private JLabel[][] squaresLabelEnemy = new JLabel[10][10];
    private JPanel oceanPanel;

    public BattleshipFrame() {
        setLayout(new GridLayout(1, 2, 20, 10));
        addOceanPanel(squaresLabelMy);
        addOceanPanel(squaresLabelEnemy);

        setSize(getPrefferedSize());
    }


    private void addOceanPanel(JLabel[][] squaresLabel) {
        oceanPanel = new JPanel();
        oceanPanel.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < squaresLabel.length; i++) {
            for (int j = 0; j < squaresLabel[i].length; j++) {
                squaresLabel[i][j] = getSquareLabel(squaresLabel[i][j]);
                oceanPanel.add(squaresLabel[i][j]);
            }
        }
        add(oceanPanel);

    }


    private JLabel getSquareLabel(JLabel squareLabel) {
        squareLabel = new JLabel(" ");
        squareLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        squareLabel.setHorizontalAlignment(JLabel.CENTER);
        squareLabel.setVerticalAlignment(JLabel.CENTER);

        return squareLabel;
    }



    public Dimension getPrefferedSize() {
      return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
