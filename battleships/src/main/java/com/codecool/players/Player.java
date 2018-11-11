package com.codecool.players;

import com.codecool.model.Ocean;
import com.codecool.oceanManagers.OceanFiller;
import com.codecool.oceanManagers.ShotResultHandler;

public abstract class Player {
    protected String[][] enemyBoard;
    protected Ocean ocean;
    protected ShotResultHandler shotResultHandler;
    private int turns = 0;

    public Player(Ocean ocean) {
        this.ocean = ocean;
        enemyBoard = new String[Ocean.SIZE][Ocean.SIZE];
        OceanFiller.fillEnemyBoard(enemyBoard);
        shotResultHandler = new ShotResultHandler(enemyBoard);
    }

    public String[][] getEnemyBoard() {
        return enemyBoard;
    }

    public Ocean getOcean() {
        return ocean;
    }

    public int getTurns() {
        return turns;
    }

    public void updateEnemyOcean(int x, int y, String shootResult) {
        shotResultHandler.smartUpdateEnemyOcean(x, y, shootResult);
        turns++;
    }
}
