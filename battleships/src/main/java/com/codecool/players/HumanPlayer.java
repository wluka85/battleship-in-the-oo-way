package com.codecool.players;

import com.codecool.model.Ocean;

public class HumanPlayer extends Player {

    private String playerName = "MentorBot";

    public HumanPlayer(Ocean ocean) {
        super(ocean);
    }

    public void setName(String newName) {
        this.playerName = newName;
    }

    public String getName() {
        return this.playerName;
    }

}