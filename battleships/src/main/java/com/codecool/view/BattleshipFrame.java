package com.codecool.view;

import com.codecool.controller.HandleGame;
import com.codecool.model.Ocean;

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
    private JMenuItem menuItemNewGame, menuItemHighScore, menuItemExit;
    private JLabel[][] squaresLabelMy = new JLabel[Ocean.SIZE][Ocean.SIZE];
    private JLabel[][] squaresLabelEnemy = new JLabel[Ocean.SIZE][Ocean.SIZE];
    private JLabel messageOfGameLabel;
    private JPanel oceanPanel, hudPanel, controlPanel;
    private String gameMode = "";
    private HandleGame handleGame;
    private JButton nextButton;
    private Ocean ocean1, ocean2;
    private int levelFirstPlayer, levelSecondPlayer;
    private int phaseGame;

    public BattleshipFrame() {
        addMenuBar();
        setLayout(new BorderLayout());
        addControlPanel();
        addHudPanel();
        add(hudPanel, BorderLayout.CENTER);
        setSize(getPrefferedSize());
    }

    private void addHudPanel() {
        hudPanel = new JPanel();
        hudPanel.setLayout(new GridLayout(1, 2, 20, 10));
        hudPanel.add(addOceanPanel(squaresLabelMy));
        hudPanel.add(addOceanPanel(squaresLabelEnemy));
    }

    private void addMenuBar() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuGame = new JMenu("Game");
        menuItemNewGame = new JMenuItem("New Game");
        menuItemHighScore = new JMenuItem("High Score");
        menuItemExit = new JMenuItem("Exit");
        menuGame.add(menuItemNewGame);
        menuGame.add(menuItemHighScore);
        menuGame.add(menuItemExit);
        menuBar.add(menuGame);

        menuItemHighScore.addActionListener(this);

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
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gameMode.equals("aivAI") && !handleGame.checkIfGameOver()) {
                    simulationAIvAI();
                } else {
                    takeATurnPvP();
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
        int[] position = getXY(source);
        if (gameMode.equals("pvAI") && !handleGame.checkIfGameOver()) {
            handleGame.takeATurn(0, position[0], position[1]);
            String[][] oceanPl = handleGame.getOceanBoard(0, 1);
            displayOcean(oceanPl, squaresLabelMy);
            handleGame.takeATurn(1);
            String[][] oceanEnemy = handleGame.getOceanBoard(0, 0);
            displayOcean(oceanEnemy, squaresLabelEnemy);
        }
         else {
             takeATurnPvP(position[0], position[1]);
        }
        if (handleGame.checkIfGameOver()) {
            JOptionPane.showMessageDialog(null, handleGame.getGameResult());
            if (gameMode.equals("pvAI")) {
                handleGame.savePlayerScore();
                gameMode = "";
            }
        }
    }

    private void takeATurnPvP() {
        if (gameMode.equals("pvp") && phaseGame == 1) {
            phaseGame += 1;
            messageOfGameLabel.setText("Player 1 take shot...");
            displayOceans(0);

        } else if (gameMode.equals("pvp") && phaseGame == 3) {
            phaseGame += 1;
            clearOceans();
            messageOfGameLabel.setText("Player 2 press next...");
        } else if (gameMode.equals("pvp") && phaseGame == 4) {
            phaseGame += 1;
            messageOfGameLabel.setText("Player 2 take shot...");
            displayOceans(1);
        } else if (gameMode.equals("pvp") && phaseGame == 6) {
            phaseGame += 1;
            messageOfGameLabel.setText("Player 1 press next...");
            clearOceans();
            phaseGame = 1;
        } else if (handleGame.checkIfGameOver()) {
            JOptionPane.showMessageDialog(null, handleGame.getGameResult());
        }
    }

    private void takeATurnPvP(int x, int y) {
        if (gameMode.equals("pvp") && !handleGame.checkIfGameOver()) {
           if (phaseGame == 2) {
               handleGame.takeATurn(0, x, y);
               displayOceans(0);
               phaseGame++;
               messageOfGameLabel.setText("Press next...");

           } else if (phaseGame == 5) {
               handleGame.takeATurn(1, x, y);
               displayOceans(1);
               messageOfGameLabel.setText("Press next...");
               phaseGame++;
           }
       }
    }

    private void displayOceans(int playerIndex) {
        String[][] oceanPl = handleGame.getOceanBoard(playerIndex, 1);
        displayOcean(oceanPl, squaresLabelMy);
        String[][] oceanEnemy = handleGame.getOceanBoard(playerIndex, 0);
        displayOcean(oceanEnemy, squaresLabelEnemy);
    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == menuItemHighScore) {
            handleGame = new HandleGame(1, 1);
            HighScoreDialog highScoreDialog = new HighScoreDialog(this, handleGame.getHighscores());

        } else if (source == menuItemNewGame){
            createNewGame();
        }
    }

    private void createNewGame() {
        clearOceans();
        ChooseGameModeDialog chooseGameMode = new ChooseGameModeDialog(this);
        gameMode = chooseGameMode.getGameMode();

        if (gameMode.equals("aivAI")) {
            createNewGameOfAivAImode();

        } else if (gameMode.equals("pvAI")) {
            createNewGameOfPvAImode();

        } else if (gameMode.equals("pvp")) {
            createNewGameOfPvPmode();
        }
    }

    private void createNewGameOfAivAImode() {
        levelFirstPlayer = getInputOfLevelFromUser("Choose level first computer: ");
        levelSecondPlayer = getInputOfLevelFromUser("Choose level second computer: ");
        handleGame = new HandleGame(levelFirstPlayer, levelSecondPlayer);
        messageOfGameLabel.setText("Press next...");
    }

    private void createNewGameOfPvAImode() {
        String playerName = JOptionPane.showInputDialog(null, "What is your name?");
        levelFirstPlayer = getInputOfLevelFromUser("Choose level: ");
        ocean1 = getGeneratedOcean();
        handleGame = new HandleGame(ocean1, levelFirstPlayer);
        handleGame.setNameOfPlayer(playerName);
    }

    private void createNewGameOfPvPmode() {
        messageOfGameLabel.setText("Player 1 place your ships...");
        ocean1 = getGeneratedOcean();
        messageOfGameLabel.setText("Player 2 place your ships...");
        ocean2 = getGeneratedOcean();
        handleGame = new HandleGame (ocean1, ocean2);
        phaseGame = 1;
        messageOfGameLabel.setText("Player 1 your turn, press next...");
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
            if (level > 4)
                level = 4;
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


    private void clearOceans() {
        for (int i = 0; i < squaresLabelMy.length; i++) {
            for (int j = 0; j < squaresLabelMy[i].length; j++) {
                squaresLabelMy[i][j].setText("");
            }
        }

        for (int i = 0; i < squaresLabelEnemy.length; i++) {
            for (int j = 0; j < squaresLabelEnemy[i].length; j++) {
                squaresLabelEnemy[i][j].setText("");
            }
        }
    }


    private int[] getXY(Object source) {
        for (int i = 0; i < squaresLabelMy.length; i++) {
            for (int j = 0; j < squaresLabelMy[i].length; j++) {
                if (source == squaresLabelMy[i][j]) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] { 0, 0 };
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
