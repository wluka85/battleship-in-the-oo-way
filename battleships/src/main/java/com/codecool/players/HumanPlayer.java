package com.codecool.players;

import com.codecool.model.Ocean;
import com.codecool.oceanManagers.ShotResultHandler;

public class HumanPlayer {

    private String[][] enemyOcean = new String[Ocean.SIZE][Ocean.SIZE];
    public Ocean ocean;
    private int turns = 0;
    private String playerName = "MentorBot";

    public HumanPlayer(Ocean ocean) {
        this.ocean = ocean;
    }

    public String[][] getEnemyBoard() {
        return enemyOcean;
    }

    public void setName(String newName) {
        this.playerName = newName;
        ;
    }

    public String getName() {
        return this.playerName;
    }

    public int getTurns() {
        return this.turns;
    }

    public void updateEnemyOcean(int x, int y, String shootResult) {
        ShotResultHandler.updateEnemyOcean(x, y, shootResult, enemyOcean);
        turns++;
    }
}