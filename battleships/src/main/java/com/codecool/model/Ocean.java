package com.codecool.model;

import com.codecool.controller.OceanValidator;

import java.util.ArrayList;
import java.util.List;

public class Ocean {
    public static int SIZE = 20;
    private String[][] ocean = new String[SIZE][SIZE];
    private List<Ship> ships = new ArrayList<>();

    public Ocean() {
        /**
         * Constructor for Ocean class
         * fills 'ocean' variable (String[][]) with (" ")
         */
        for (int i = 0; i < ocean.length; i++) {
            for (int j = 0; j < ocean[i].length; j++) {
                ocean[i][j] = " ";
            }
        }
    }

    public void addShip(Ship ship) {
        /**
         * Adds 'Ship' object to ocean, if ship is 
         * placed correctly (accordingly to the rules)
         */
        boolean valid = OceanValidator.validateOcean(ocean, ship);
        if (valid) {
            ships.add(ship);
            addShipToOcean(ship);
        }
    }

    private void addShipToOcean(Ship ship) {
        /**
         * Adds visual interpretation of 'Ship' object
         * on 'ocean' (String[][])
         */
        for (int i = 0; i < ship.getShipSize(); i++) {
            int indexX = ship.getXOfSquare(i);
            int indexY = ship.getYOfSquare(i);
            ocean[indexX][indexY] = "S";
        }
    }

    public String takeShot(int x, int y) {
        /**
         * Method updates ocean after shot.
         * If 'Ship' placed on 'ocean' was hit, it is beeing
         * replaced with "X", else with "O"
         *
         * returns String representing result of shot
         */
        if (ocean[x][y].equals("S")) {
            ocean[x][y] = "X";
            int hitShip = -1;
            for (int i = 0; i < ships.size(); i++) {
                if (ships.get(i).checkIfShip(x, y)) {
                    ships.get(i).decrementHealthPoints();
                    hitShip = i;
                }
            }
            if (ships.get(hitShip).getHealthPoints() < 1) {
                return "Hit and sunk";
            } else {
                return "Hit";
            }
        } else {
            ocean[x][y] = "O";
            return "Miss";
        }
    }

    public String[][] getOceanBoard() {
        /**
         * Accessor method for variable 'ocean'
         */
        return ocean;
    }
}
