import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class ShipsPositioningDialog extends JDialog implements MouseListener {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 550;
    private JLabel[][] squaresLabelMy = new JLabel[10][10];
    private JLabel nameShipLabel;
    private JPanel oceanPanel, optionsOfShipPanel;
    private ButtonGroup radioButtonGroup;
    private JRadioButton verticalButton, horizontalButton;
    private Ship ship;
    private int lengthShip = 5;



    public ShipsPositioningDialog(JFrame owner) {
        super(owner, "Add ships", true);
        setLayout(new BorderLayout());
        addOceanPanel(squaresLabelMy);
        addOptionsOfShips();
        setSize(getPrefferedSize());
        setResizable(false);
        setVisible(true);

    }


    private void addOceanPanel(JLabel[][] squaresLabel) {
        oceanPanel = new JPanel();
        oceanPanel.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < squaresLabel.length; i++) {
            for (int j = 0; j < squaresLabel[i].length; j++) {
                squaresLabel[i][j] = getSquareLabel(squaresLabel[i][j]);
                squaresLabel[i][j].addMouseListener(this);
                oceanPanel.add(squaresLabel[i][j]);
            }
        }
        add(oceanPanel, BorderLayout.CENTER);
    }


    private JLabel getSquareLabel(JLabel squareLabel) {
        squareLabel = new JLabel(" ");
        squareLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        squareLabel.setHorizontalAlignment(JLabel.CENTER);
        squareLabel.setVerticalAlignment(JLabel.CENTER);

        return squareLabel;
    }


    private void addOptionsOfShips() {
        radioButtonGroup = new ButtonGroup();
        optionsOfShipPanel = new JPanel();
        nameShipLabel = new JLabel(namesShips[0]);
        verticalButton = new JRadioButton("Vertical", false);
        horizontalButton = new JRadioButton("Horizontal", true);
        verticalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              vertical = false;
            }
        });
        horizontalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              vertical = true;
            }
        });
        optionsOfShipPanel.add(nameShipLabel);
        radioButtonGroup.add(verticalButton);
        radioButtonGroup.add(horizontalButton);
        optionsOfShipPanel.add(verticalButton);
        optionsOfShipPanel.add(horizontalButton);

        add(optionsOfShipPanel, BorderLayout.SOUTH);
    }


    public Dimension getPrefferedSize() {
      return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
