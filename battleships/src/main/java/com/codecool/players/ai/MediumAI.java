package com.codecool.players.ai;

import com.codecool.model.Ocean;
import com.codecool.model.Point;
import com.codecool.players.AI;

public class MediumAI extends AI {

    public MediumAI(Ocean ocean) {
        super(ocean);
    }

    @Override
    public void takeAShoot(Ocean enemyOcean) {
        standardShot(enemyOcean);
    }
}
