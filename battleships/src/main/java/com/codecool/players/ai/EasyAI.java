package com.codecool.players.ai;

import com.codecool.model.Ocean;
import com.codecool.players.AI;

import java.util.Random;

public class EasyAI extends AI {

    EasyAI(Ocean ocean) {
        super(ocean);
    }

    @Override
    public void takeAShoot(Ocean enemyOcean) {
        int x, y;
        boolean isWater;
        Random generator = new Random();
        do {
            x = generator.nextInt(Ocean.SIZE);
            y = generator.nextInt(Ocean.SIZE);

            isWater = enemyBoard[x][y].equals(" ");

        } while (!isWater);

        String shootResult = enemyOcean.takeShot(x, y);

        shotResultHandler.updateEnemyOcean(x, y, shootResult);
    }
}
