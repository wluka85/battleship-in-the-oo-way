package com.codecool.players.ai;

import com.codecool.model.Ocean;
import com.codecool.players.AI;

public class MasterAI extends AI {

    public MasterAI(Ocean ocean) {
        super(ocean);
        this.huntMode = false;
    }

    @Override
    public void takeAShoot(Ocean enemyOcean) {
        if (huntMode) {
            attackDamagedShip(enemyOcean);
        } else {
            bestMove(enemyOcean);
        }
    }

    private void bestMove(Ocean enemyOcean) {
        /**
         * Method used by AI on level 4 or higher
         *
         * AI looks for best move.
         * If not found, picks random square;
         */
        boolean foundBestMove = makeBestMove(enemyOcean);

        if (!foundBestMove) {
            standardShot(enemyOcean);
        }
    }

    private boolean makeBestMove(Ocean enemyOcean) {
        /**
         * Method used by AI on level 4 or higher
         * Best move is move which checks more than one square
         * Example:
         *    X     Since '1' has only 1 unchecked
         *  X 1 2   adjacent square ('2') its better to
         *    X     check square '2', because if its empty,
         * '1' is also empty (there are no ships of length 1)
         */

        int uncheckedAdjacent;

        for (int x = 0; x < Ocean.SIZE; x++) {
            for (int y = 0; y < Ocean.SIZE; y++) {
                if (enemyBoard.getBoard()[x][y].equals(" ")) {
                    uncheckedAdjacent = countUncheckedAdjacent(x, y);
                    if (uncheckedAdjacent == 1) {
                        attackAdjacent(x, y, enemyOcean);
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
