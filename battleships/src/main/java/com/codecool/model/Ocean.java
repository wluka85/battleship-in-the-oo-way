package com.codecool.model;

import com.codecool.controller.OceanValidator;

import java.util.ArrayList;
import java.util.List;

public class Ocean {
    public static int SIZE = 10;
    private String[][] ocean = new String[SIZE][SIZE];
    private List<Ship> ships = new ArrayList<>();

    public Ocean() {
        for (int i = 0; i < ocean.length; i++) {
            for (int j = 0; j < ocean[i].length; j++) {
                ocean[i][j] = " ";
            }
        }
    }

    public void addShip(Ship ship) {
        boolean valid = OceanValidator.validateOcean(ocean, ship);
        if (valid) {
            ships.add(ship);
            addShipToOcean(ship);
        }
    }

    private void addShipToOcean(Ship ship) {
        for (int i = 0; i < ship.getShipSize(); i++) {
            int indexX = ship.getXOfSquare(i);
            int indexY = ship.getYOfSquare(i);
            ocean[indexX][indexY] = "S";
        }
    }

    public String takeShot(int x, int y) {
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
        return ocean;
    }
}
