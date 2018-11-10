package com.codecool.view;

import com.codecool.oceanManagers.OceanValidator;
import com.codecool.model.Ocean;
import com.codecool.model.Ship;

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
    private Ocean ocean = new Ocean();
    private boolean vertical = true;
    private final static String[] namesShips = { "Carrier 5", "Battleship 4", "Cruiser 3", "Submarine 3",
            "Destroyer 2" };
    private int counterShip = 0;
    private String nameShip = "Carrier 5";

    public ShipsPositioningDialog(JFrame owner) {
        super(owner, "Add ships", true);
        setLayout(new BorderLayout());
        addOceanPanel(squaresLabelMy);
        addOptionsOfShips();
        setSize(getPrefferedSize());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        int[] position = getXY(source);
        lengthShip = getLengthShip(nameShip);
        ship = new Ship(position[0], position[1], lengthShip, vertical);

        if (OceanValidator.validateOcean(ocean.getOceanBoard(), ship)) {
            ocean.addShip(ship);
            displayOcean(ocean);
            counterShip += 1;

            if (counterShip == 5) {
                dispose();

            } else {
                nameShip = getNameShip(counterShip);
                nameShipLabel.setText(nameShip);
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

    private void displayOcean(Ocean ocean) {
        String[][] oceanBoard = new String[10][10];
        oceanBoard = ocean.getOceanBoard();
        for (int i = 0; i < squaresLabelMy.length; i++) {
            for (int j = 0; j < squaresLabelMy[i].length; j++) {
                squaresLabelMy[i][j].setText(oceanBoard[i][j]);
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

    private int getLengthShip(String nameShip) {
        if (nameShip.equals("Carrier 5")) {
            return 5;
        }
        if (nameShip.equals("Battleship 4")) {
            return 4;
        }
        if (nameShip.equals("Cruiser 3")) {
            return 3;
        }
        if (nameShip.equals("Submarine 3")) {
            return 3;
        }
        if (nameShip.equals("Destroyer 2")) {
            return 2;
        }
        return 0;
    }

    private String getNameShip(int counterShip) {
        return namesShips[counterShip];
    }

    public Ocean getOcean() {
        return ocean;
    }
}
