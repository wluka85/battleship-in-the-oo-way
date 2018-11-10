package com.codecool.players.ai;

import com.codecool.model.Ocean;
import com.codecool.players.AI;

public class HardAI extends AI {

    HardAI(Ocean ocean) {
        super(ocean);
        this.huntMode = false;
    }

    @Override
    public void takeAShoot(Ocean enemyOcean) {
        if (huntMode) {
            attackDamagedShip(enemyOcean);
        } else {
            standardShot(enemyOcean);
        }

    }
}