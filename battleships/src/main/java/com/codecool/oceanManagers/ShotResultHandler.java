package com.codecool.oceanManagers;

public class ShotResultHandler {

    public static void updateEnemyOcean(int x, int y, String shootResult, String[][] ocean) {
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
}
