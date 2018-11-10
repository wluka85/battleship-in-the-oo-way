package com.codecool.players.ai;

import com.codecool.model.Ocean;
import com.codecool.players.AI;

public class AIFactory {

    public AI createAIPlayer(Ocean ocean, int level) {
        switch (level) {
            case 2:
                return new MediumAI(ocean);
            case 3:
                return new HardAI(ocean);
            case 4:
                return new MasterAI(ocean);
            default:
                return new EasyAI(ocean);
        }
    }
}
