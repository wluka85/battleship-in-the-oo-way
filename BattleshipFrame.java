import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.InterruptedException;
import java.lang.Thread;


public class BattleshipFrame extends JFrame implements MouseListener, ActionListener {
    private static final int DEFAULT_WIDTH = 1020;
    private static final int DEFAULT_HEIGHT = 500;
    private JMenuBar menuBar;
    private JMenu menuGame;
    private JMenuItem menuItemNewGame, menuItemExit;
    private JLabel[][] squaresLabelMy = new JLabel[10][10];
    private JLabel[][] squaresLabelEnemy = new JLabel[10][10];
    private JLabel messageOfGameLabel;
    private JPanel oceanPanel, hudPanel, controlPanel;
    private String gameMode = "";
    private HandleGame handleGame;
    private JButton nextButton;
    private Ocean ocean1, ocean2;
    private int levelFirstPlayer, levelSecondPlayer;

    public BattleshipFrame() {
        addMenuBar();
        setLayout(new BorderLayout());
        hudPanel = new JPanel();
        hudPanel.setLayout(new GridLayout(1, 2, 20, 10));
        hudPanel.add(addOceanPanel(squaresLabelMy));
        hudPanel.add(addOceanPanel(squaresLabelEnemy));
        addControlPanel();
        add(hudPanel, BorderLayout.CENTER);
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


    private void addControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 2, 500, 10));
        messageOfGameLabel = new JLabel("Choose new Game...");
        add(controlPanel, BorderLayout.SOUTH);
        nextButton = new JButton("Next");
        controlPanel.add(messageOfGameLabel);
        controlPanel.add(nextButton);
        nextButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (gameMode.equals("aivAI") && !handleGame.checkIfGameOver()) {
                    simulationAIvAI();
                }
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
        int[] position =  getXY(source);
        if (gameMode.equals("pvAI") && !handleGame.checkIfGameOver()){
            handleGame.takeATurn(0, position[0], position[1]);
            String[][] oceanPl = handleGame.getOceanBoard(0, 1);
            displayOcean(oceanPl, squaresLabelMy);
            handleGame.takeATurn(1);
            String[][] oceanEnemy = handleGame.getOceanBoard(0, 0);
            displayOcean(oceanEnemy, squaresLabelEnemy);
        }
    }


    public void actionPerformed(ActionEvent e) {
        ChooseGameModeDialog chooseGameMode = new ChooseGameModeDialog(this);
        gameMode = chooseGameMode.getGameMode();
        if (gameMode.equals("aivAI")) {
            levelFirstPlayer = getInputOfLevelFromUser("Choose level first computer: ");
            levelSecondPlayer = getInputOfLevelFromUser("Choose level second computer: ");
            handleGame = new HandleGame (levelFirstPlayer, levelSecondPlayer);
            messageOfGameLabel.setText("Press next...");
        }

        if (gameMode.equals("pvAI")) {
            levelFirstPlayer = getInputOfLevelFromUser("Choose level: ");
            ocean1 = getGeneratedOcean();
            handleGame = new HandleGame (ocean1, levelFirstPlayer);

        }
    }


    private Ocean getGeneratedOcean() {
        ShipsPositioningDialog shipsPositioning = new ShipsPositioningDialog(this);
        Ocean ocean = new Ocean();
        ocean = shipsPositioning.getOcean();
        messageOfGameLabel.setText("Type your ships on ocean...");
        return ocean;
    }


    private int getInputOfLevelFromUser(String text) {
        String levelStr = "";
        int level = 0;
        while (level < 1) {
            levelStr = JOptionPane.showInputDialog(null, text);
            try {
                level = Integer.parseInt(levelStr);
            } catch (NumberFormatException ne) {

            }
            if (level > 4) level = 4;
        }
        return level;
    }


    private void simulationAIvAI() {
            handleGame.takeATurn(0);
            String[][] oceanAI1 = handleGame.getOceanBoard(0, 1);
            displayOcean(oceanAI1, squaresLabelMy);
            handleGame.takeATurn(1);
            String[][] oceanAI2 = handleGame.getOceanBoard(1, 1);
            displayOcean(oceanAI2, squaresLabelEnemy);
    }


    private void displayOcean(String[][] oceanBoard, JLabel[][] squaresLabel) {
        for (int i = 0; i < squaresLabel.length; i++) {
            for (int j = 0; j < squaresLabel[i].length; j++) {
                squaresLabel[i][j].setText(oceanBoard[i][j]);
            }
        }
    }


    private int[] getXY(Object source) {
        for (int i = 0; i < squaresLabelMy.length; i++) {
            for (int j = 0; j < squaresLabelMy[i].length; j++) {
                if(source == squaresLabelMy[i][j]) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {0, 0};
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
