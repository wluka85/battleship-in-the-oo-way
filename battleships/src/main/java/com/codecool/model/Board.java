package com.codecool.model;

import com.codecool.oceanManagers.OceanFiller;

public class Board {

    private String[][] board;

    public Board() {
        this.board = new String[Ocean.SIZE][Ocean.SIZE];
        OceanFiller.fillEnemyBoard(this.board);
    }

    public String[][] getBoard() {
        return board;
    }
}
