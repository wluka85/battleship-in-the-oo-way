import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class BattleshipFrame extends JFrame implements MouseListener, ActionListener {
    private static final int DEFAULT_WIDTH = 1020;
    private static final int DEFAULT_HEIGHT = 500;
    private JMenuBar menuBar;
    private JMenu menuGame;
    private JMenuItem menuItemNewGame, menuItemExit;
    private JLabel[][] squaresLabelMy = new JLabel[10][10];
    private JLabel[][] squaresLabelEnemy = new JLabel[10][10];
    private JPanel oceanPanel, hudPanel;
    private String gameMode = "";
    private HandleGame handleGame;

    public BattleshipFrame() {
        addMenuBar();
        setLayout(new BorderLayout());
        hudPanel = new JPanel();
        hudPanel.setLayout(new GridLayout(1, 2, 20, 10));
        hudPanel.add(addOceanPanel(squaresLabelMy));
        hudPanel.add(addOceanPanel(squaresLabelEnemy));
        add(hudPanel);
        setSize(getPrefferedSize());
    }


    private void addMenuBar() {
      menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      menuGame = new JMenu("Game");
      menuItemNewGame = new JMenuItem("New Game");
      menuItemExit = new JMenuItem("Exit");
      menuGame.add(menuItemNewGame);
      menuGame.add(menuItemExit);
      menuBar.add(menuGame);

      menuItemNewGame.addActionListener(this);

      menuItemExit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          dispose();
        }
      });
    }


    private JPanel addOceanPanel(JLabel[][] squaresLabel) {
        oceanPanel = new JPanel();
        oceanPanel.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < squaresLabel.length; i++) {
            for (int j = 0; j < squaresLabel[i].length; j++) {
                squaresLabel[i][j] = getSquareLabel(squaresLabel[i][j]);
                squaresLabel[i][j].addMouseListener(this);
                oceanPanel.add(squaresLabel[i][j]);
            }
        }
        return oceanPanel;

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


    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        for (int i = 0; i < squaresLabelMy.length; i++) {
            for (int j = 0; j < squaresLabelMy[i].length; j++) {
                if(source == squaresLabelMy[i][j]) {

                }
            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        ChooseGameModeDialog chooseGameMode = new ChooseGameModeDialog(this);
        gameMode = chooseGameMode.getGameMode();
        if (gameMode.equals("aivAI")) {
            handleGame = new HandleGame (1, 2);
            simulationAIvAI();
        }
    }


    private void simulationAIvAI() {
        while (!handleGame.checkIfGameOver()) {
            handleGame.takeATurn(0);
            String[][] oceanAI1 = handleGame.getOceanBoard(0, 1);
            displayOcean(oceanAI1, squaresLabelMy);
            handleGame.takeATurn(1);
            String[][] oceanAI2 = handleGame.getOceanBoard(1, 1);
            displayOcean(oceanAI1, squaresLabelEnemy);
        }
    }


    private void displayOcean(String[][] oceanBoard, JLabel[][] squaresLabel) {
        for (int i = 0; i < squaresLabel.length; i++) {
            for (int j = 0; j < squaresLabel[i].length; j++) {
                squaresLabel[i][j].setText(oceanBoard[i][j]);
            }
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    }


    @Override
    public void mouseExited(MouseEvent e) {
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }


     @Override
     public void mouseReleased(MouseEvent e) {
    }
}
