package com.codecool.oceanManagers;

import com.codecool.model.Ocean;

public class OceanFiller {

    public static void fillEnemyBoard(String[][] ocean) {
        for (int x = 0; x < Ocean.SIZE; x++) {
            for (int y = 0; y < Ocean.SIZE; y++) {
                ocean[x][y] = " ";
            }
        }
    }
}
