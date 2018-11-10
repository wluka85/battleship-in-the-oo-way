package com.codecool.oceanManagers;

import com.codecool.model.Ocean;

public class ShotResultHandler {

    private String[][] ocean;

    public ShotResultHandler(String[][] ocean) {
        this.ocean = ocean;
    }

    public void updateEnemyOcean(int x, int y, String shootResult) {
        switch (shootResult) {
            case "Hit":
            case "Hit and sunk":
                ocean[x][y] = "X";
                break;
            case "Miss":
                ocean[x][y] = "O";
                break;
        }
    }

    public void smartUpdateEnemyOcean(int x, int y, String shootResult) {
        switch (shootResult) {
            case "Hit":
                handleHit(x, y);
                break;
            case "Hit and sunk":
                handleHitAndSunk(x, y);
                break;
            case "Miss":
                ocean[x][y] = "O";
                break;
        }
    }


    private void handleHit(int x, int y) {
        ocean[x][y] = "X";

        if (x - 1 >= 0 && y - 1 >= 0) {
            ocean[x - 1][y - 1] = "O";
        }

        if (x - 1 >= 0 && y + 1 < Ocean.SIZE) {
            ocean[x - 1][y + 1] = "O";
        }

        if (x + 1 < Ocean.SIZE && y - 1 >= 0) {
            ocean[x + 1][y - 1] = "O";
        }

        if (x + 1 < Ocean.SIZE && y + 1 < Ocean.SIZE) {
            ocean[x + 1][y + 1] = "O";
        }
    }


    private void handleHitAndSunk(int x, int y) {
        handleHit(x, y);
        markTop(x, y);
        markDown(x, y);
        markLeft(x, y);
        markRight(x, y);
    }

    private void markTop(int x, int y) {
        for (; y >= 0; y--) {
            if (!ocean[x][y].equals("X")) {
                ocean[x][y] = "O";
                break;
            }
        }
    }

    private void markDown(int x, int y) {
        for (int i = y; i < Ocean.SIZE; i++) {
            if (!ocean[x][i].equals("X")) {
                ocean[x][i] = "O";
                break;
            }
        }
    }

    private void markLeft(int x, int y) {
        for (int i = x; i >= 0; i--) {
            if (!ocean[i][y].equals("X")) {
                ocean[i][y] = "O";
                break;
            }
        }
    }

    private void markRight(int x, int y) {
        for (int i = x; i < Ocean.SIZE; i++) {
            if (!ocean[i][y].equals("X")) {
                ocean[i][y] = "O";
                break;
            }
        }
    }
}
