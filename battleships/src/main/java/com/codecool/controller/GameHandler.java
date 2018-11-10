package com.codecool.controller;

import com.codecool.model.Ocean;
import com.codecool.players.AI;
import com.codecool.players.Player;
import com.codecool.players.ai.AIFactory;

import java.util.ArrayList;
import java.util.List;

public class GameHandler {

    private List<Object> players = new ArrayList<>();
    private List<Ocean> oceans = new ArrayList<>();
    private AIFactory aiFactory = new AIFactory();

    public GameHandler(int level1, int level2) {
        aivAI(level1, level2);
    }

    public GameHandler(Ocean ocean, int level) {
        pvAI(ocean, level);
    }

    public GameHandler(Ocean ocean1, Ocean ocean2) {
        pvp(ocean1, ocean2);
    }

    private void pvp(Ocean ocean1, Ocean ocean2) {
        oceans.add(ocean1);
        players.add(new Player(ocean1));

        oceans.add(ocean2);
        players.add(new Player(ocean2));

    }

    private void pvAI(Ocean ocean, int level) {
        oceans.add(ocean);
        players.add(new Player(ocean));

        Ocean secondPlayerOcean = new Ocean();
        oceans.add(secondPlayerOcean);
        AI ai = aiFactory.createAIPlayer(secondPlayerOcean, level);
        players.add(ai);

    }

    private void aivAI(int level1, int level2) {
        Ocean firstPlayerOcean = new Ocean();
        oceans.add(firstPlayerOcean);
        AI firstPlayer = aiFactory.createAIPlayer(firstPlayerOcean, level1);
        players.add(firstPlayer);

        Ocean secondPlayerOcean = new Ocean();
        oceans.add(secondPlayerOcean);
        AI secondPlayer = aiFactory.createAIPlayer(secondPlayerOcean, level2);
        players.add(secondPlayer);
    }

    public boolean checkIfGameOver() {
        Ocean ocean1 = oceans.get(0);
        Ocean ocean2 = oceans.get(1);
        return (checkOcean(ocean1) || checkOcean(ocean2));
    }

    private boolean checkOcean(Ocean ocean) {
        String[][] board = ocean.getOceanBoard();
        for (String[] row : board) {
            for (String square : row) {
                if (square.equals("S")) {
                    return false;
                }
            }
        }

        return true;
    }

    public void takeATurn(int currentPlayerIndex, int x, int y) {
        int nextPlayerIndex = getNextPlayerIndex(currentPlayerIndex);
        String result = oceans.get(nextPlayerIndex).takeShot(x, y);

        //TODO refactor Player and AI classes to avoid casting
        Player currentPlayer = (Player) players.get(currentPlayerIndex);
        currentPlayer.updateEnemyOcean(x, y, result);
    }

    public void takeATurn(int playerIndex) {
        int nextPlayerIndex = getNextPlayerIndex(playerIndex);
        AI currentPlayer = (AI) players.get(playerIndex);
        currentPlayer.takeAShoot(oceans.get(nextPlayerIndex));
    }

    private int getNextPlayerIndex(int currentPlayerIndex) {
        return (currentPlayerIndex + 1) % 2;
    }

    public String[][] getOceanBoard(int playerIndex, int boardNumber) {
        if (boardNumber == 0) {
            return oceans.get(playerIndex).getOceanBoard();
        } else {
            if (players.get(playerIndex).getClass().getName().contains("AI")) {
                AI currentPlayer = (AI) players.get(playerIndex);
                return currentPlayer.getEnemyBoard();
            } else {
                Player currentPlayer = (Player) players.get(playerIndex);
                return currentPlayer.getEnemyBoard();
            }

        }
    }

    public void setNameOfPlayer(String newName) {
        Player player = (Player) players.get(0);
        player.setName(newName);
    }

    public String getHighScores() {
        List<String> highScoreList = Highscore.getDisplayableHighscoreList();
        return String.join("\n", highScoreList);
    }

    public String getGameResult() {
        Ocean player1Ocean = oceans.get(0);
        boolean player1Lost = checkOcean(player1Ocean);

        if (player1Lost) {
            return "Player 2 won!";
        } else {
            return "Player 1 won!";
        }
    }

    public void savePlayerScore() {
        Player player = (Player) players.get(0);
        String playerName = player.getName();
        int turns = player.getTurns();
        Highscore.addNewScore(playerName, turns);
    }
}
