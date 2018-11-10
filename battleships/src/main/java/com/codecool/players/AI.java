package com.codecool.players;

import com.codecool.controller.OceanValidator;
import com.codecool.model.Ocean;
import com.codecool.model.Ship;

import java.util.Random;

public class AI {

    private static final int OCEAN_SIZE = 10;
    private String[][] enemyBoard = new String[10][10];
    public Ocean ocean;
    private int level;
    private boolean huntMode = false;
    // huntMode is used only by AI on level 3

    public AI(Ocean ocean, int level) {
        /**
         * AI constructor
         * Saves its ocean and level
         * 
         * Ocean stores ships
         * level determines how AI plays
         */
        this.ocean = ocean;
        this.level = level;
        fillEnemyBoard();
        addShipsToOcean();
    }

    private void fillEnemyBoard() {
        /**
         * Fills String[][] enemyBoard
         * On start enemyBoard is filled
         * with empty fields (whitespaces)
         */
        for (int x = 0; x < OCEAN_SIZE; x++) {
            for (int y = 0; y < OCEAN_SIZE; y++) {
                enemyBoard[x][y] = " ";
            }
        }
    }

    private void addShipsToOcean() {
        /**
         * Method used by AI to randomly
         * place ships on Ocean board
         */
        addCarrier();
        addBattleship();
        addCruiser();
        addCruiser();
        addDestroyer();

    }

    private void addCarrier() {
        /**
         * Method used to place a ship
         * of length 5.
         */
        Ship carrier;
        boolean validShipLocation;
        do {
            Random generator = new Random();
            int x = generator.nextInt(OCEAN_SIZE);
            int y = generator.nextInt(OCEAN_SIZE);
            boolean vertical = generator.nextBoolean();
            carrier = new Ship(x, y, 5, vertical);
            validShipLocation = OceanValidator.validateOcean(ocean.getOceanBoard(), carrier);
        } while (!validShipLocation);
        ocean.addShip(carrier);
    }

    private void addBattleship() {
        /**
         * Method used to place a ship
         * of length 4.
         */
        Ship battleship;
        boolean validShipLocation;
        do {
            Random generator = new Random();
            int x = generator.nextInt(OCEAN_SIZE);
            int y = generator.nextInt(OCEAN_SIZE);
            boolean vertical = generator.nextBoolean();
            battleship = new Ship(x, y, 4, vertical);
            validShipLocation = OceanValidator.validateOcean(ocean.getOceanBoard(), battleship);
        } while (!validShipLocation);
        ocean.addShip(battleship);
    }

    private void addCruiser() {
        /**
         * Method used to place a ship
         * of length 3.
         */
        Ship cruiser;
        boolean validShipLocation;
        do {
            Random generator = new Random();
            int x = generator.nextInt(OCEAN_SIZE);
            int y = generator.nextInt(OCEAN_SIZE);
            boolean vertical = generator.nextBoolean();
            cruiser = new Ship(x, y, 3, vertical);
            validShipLocation = OceanValidator.validateOcean(ocean.getOceanBoard(), cruiser);
        } while (!validShipLocation);
        ocean.addShip(cruiser);
    }

    private void addDestroyer() {
        /**
         * Method used to place a ship
         * of length 2.
         * 
         */
        Ship destroyer;
        boolean validShipLocation;
        do {
            Random generator = new Random();
            int x = generator.nextInt(OCEAN_SIZE);
            int y = generator.nextInt(OCEAN_SIZE);
            boolean vertical = generator.nextBoolean();
            destroyer = new Ship(x, y, 2, vertical);
            validShipLocation = OceanValidator.validateOcean(ocean.getOceanBoard(), destroyer);
        } while (!validShipLocation);
        ocean.addShip(destroyer);
    }

    public void takeAShoot(Ocean enemyOcean) {
        /**
         * Method used by AI to take a shot
         * 
         * AI with higher level uses more
         * advanced algorithms
         * 
         */
        if (level == 1) {
            easyAIMove(enemyOcean);
        } else if (level == 2) {
            mediumAIMove(enemyOcean);
        } else if (level == 3) {
            hardAIMove(enemyOcean);
        } else if (level == 4) {
            masterAIMove(enemyOcean);
        }
    }

    private void easyAIMove(Ocean enemyOcean) {
        /**
         * Easy Move:
         * AI randomly picks square to shoot
         *  and marks it accordingly to result
         *  (miss / hit / hit and sunk)
         * 
         */
        int x, y;
        boolean isWater;
        do {
            Random generator = new Random();
            x = generator.nextInt(OCEAN_SIZE);
            y = generator.nextInt(OCEAN_SIZE);

            isWater = enemyBoard[x][y].equals(" ");

        } while (!isWater);

        String shootResult = enemyOcean.takeShot(x, y);

        switch (shootResult) {
        case "Hit":
        case "Hit and sunk":
            enemyBoard[x][y] = "X";
            break;
        case "Miss":
            enemyBoard[x][y] = "O";
            break;
        }
    }

    private void mediumAIMove(Ocean enemyOcean) {
        /**
         * Medium Move:
         * AI randomly picks square to shoot
         *  and marks it accordingly to result
         *  (miss / hit / hit and sunk)
         * 
         * On hit, AI marks as many squares as it can
         * (squares placed diagonally)
         * 
         */
        int x, y;
        boolean isWater;
        do {
            Random generator = new Random();
            x = generator.nextInt(OCEAN_SIZE);
            y = generator.nextInt(OCEAN_SIZE);

            isWater = enemyBoard[x][y].equals(" ") && countUncheckedAdjacent(x, y) != 0;

        } while (!isWater);

        String shootResult = enemyOcean.takeShot(x, y);

        switch (shootResult) {
        case "Hit":
            handleHit(x, y);
            huntMode = true;
            break;
        case "Hit and sunk":
            handleHitAndSunk(x, y);
            break;
        case "Miss":
            enemyBoard[x][y] = "O";
            break;
        }
    }

    private void hardAIMove(Ocean enemyOcean) {
        /**
         * AI behaves on 2 different ways
         * 
         * Normal: randomly shoots untill hits something
         * Hunt: attacks damaged ship untill destroyed
         * 
         */

        if (huntMode) {
            attackDamagedShip(enemyOcean);
        } else {
            mediumAIMove(enemyOcean);
        }
    }

    private void masterAIMove(Ocean enemyOcean) {
        /**
         * AI behaves on 2 different ways
         * 
         * Normal: randomly shoots untill hits something
         * Hunt: attacks damaged ship untill destroyed
         * 
         */

        if (huntMode) {
            attackDamagedShip(enemyOcean);
        } else {
            bestMove(enemyOcean);
        }
    }

    private void attackDamagedShip(Ocean enemyOcean) {
        /**
         * Searches for damaged ship part
         * and attacks squares adjacent to it
         * 
         */
        for (int x = 0; x < OCEAN_SIZE; x++) {
            for (int y = 0; y < OCEAN_SIZE; y++) {
                if (enemyBoard[x][y].equals("X") && hasUncheckedAdjacent(x, y)) {
                    attackAdjacent(x, y, enemyOcean);
                    return;
                }
            }
        }
    }

    private void handleHit(int x, int y) {
        /**
         * Method marks enemyBoard on hit.
         * 
         *  'X' - place where ship was
         *  'O' - squares placed diagonally to where ship was
         */

        enemyBoard[x][y] = "X";

        if (x - 1 >= 0 && y - 1 >= 0) {
            enemyBoard[x - 1][y - 1] = "O";
        }

        if (x - 1 >= 0 && y + 1 < OCEAN_SIZE) {
            enemyBoard[x - 1][y + 1] = "O";
        }

        if (x + 1 < OCEAN_SIZE && y - 1 >= 0) {
            enemyBoard[x + 1][y - 1] = "O";
        }

        if (x + 1 < OCEAN_SIZE && y + 1 < OCEAN_SIZE) {
            enemyBoard[x + 1][y + 1] = "O";
        }
    }

    private void handleHitAndSunk(int x, int y) {
        /**
         * Method marks all squares around destroyed ship
         * 
         */
        handleHit(x, y);
        markTop(x, y);
        markDown(x, y);
        markLeft(x, y);
        markRight(x, y);
    }

    private void markTop(int x, int y) {
        /**
         * Method marks unmarked square above shot square;
         */
        for (int i = y; i >= 0; i--) {
            if (enemyBoard[x][i].equals("X")) {
                continue;
            } else {
                enemyBoard[x][i] = "O";
                break;
            }
        }
    }

    private void markDown(int x, int y) {
        /**
         * Method marks unmarked square under shot square;
         */
        for (int i = y; i < OCEAN_SIZE; i++) {
            if (enemyBoard[x][i].equals("X")) {
                continue;
            } else {
                enemyBoard[x][i] = "O";
                break;
            }
        }
    }

    private void markLeft(int x, int y) {
        /**
         * Method marks unmarked square on left of shot square;
         */
        for (int i = x; i >= 0; i--) {
            if (enemyBoard[i][y].equals("X")) {
                continue;
            } else {
                enemyBoard[i][y] = "O";
                break;
            }
        }
    }

    private void markRight(int x, int y) {
        /**
         * Method marks unmarked square on right of shot square;
         */
        for (int i = x; i < OCEAN_SIZE; i++) {
            if (enemyBoard[i][y].equals("X")) {
                continue;
            } else {
                enemyBoard[i][y] = "O";
                break;
            }
        }
    }

    private boolean hasUncheckedAdjacent(int x, int y) {
        /**
         * Method checks if there are
         * unmarked squares adjacent to
         * square on x, y coordinates
         * 
         */
        return (uncheckedLeft(x, y) || uncheckedRight(x, y) || uncheckedTop(x, y) || uncheckedBottom(x, y));
    }

    private boolean uncheckedLeft(int x, int y) {
        /**
         * Method checks if there is
         * unmarked square on left of
         * square on x, y coordinates
         * 
         */
        return (x - 1 >= 0 && enemyBoard[x - 1][y].equals(" "));
    }

    private boolean uncheckedRight(int x, int y) {
        /**
         * Method checks if there is
         * unmarked square on right of
         * square on x, y coordinates
         * 
         */
        return (x + 1 < OCEAN_SIZE && enemyBoard[x + 1][y].equals(" "));
    }

    private boolean uncheckedTop(int x, int y) {
        /**
         * Method checks if there is
         * unmarked square above
         * square on x, y coordinates
         * 
         */
        return (y - 1 >= 0 && enemyBoard[x][y - 1].equals(" "));
    }

    private boolean uncheckedBottom(int x, int y) {
        /**
         * Method checks if there is
         * unmarked square below
         * square on x, y coordinates
         * 
         */
        return (y + 1 < OCEAN_SIZE && enemyBoard[x][y + 1].equals(" "));
    }

    private void attackAdjacent(int x, int y, Ocean enemyOcean) {
        /**
         * Method used by AI to take a shot
         * at unmarked square adjacent to 
         * undestroyed ship
         * 
         */

        int x2, y2;
        String shootResult;
        if (uncheckedLeft(x, y)) {
            shootResult = enemyOcean.takeShot(x - 1, y);
            x2 = x - 1;
            y2 = y;
        } else if (uncheckedRight(x, y)) {
            shootResult = enemyOcean.takeShot(x + 1, y);
            x2 = x + 1;
            y2 = y;
        } else if (uncheckedTop(x, y)) {
            shootResult = enemyOcean.takeShot(x, y - 1);
            x2 = x;
            y2 = y - 1;
        } else {
            shootResult = enemyOcean.takeShot(x, y + 1);
            x2 = x;
            y2 = y + 1;
        }

        switch (shootResult) {
        case "Hit":
            handleHit(x2, y2);
            huntMode = true;
            break;
        case "Hit and sunk":
            handleHitAndSunk(x2, y2);
            huntMode = false;
            break;
        case "Miss":
            enemyBoard[x2][y2] = "O";
            break;
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
            mediumAIMove(enemyOcean);
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

        for (int x = 0; x < OCEAN_SIZE; x++) {
            for (int y = 0; y < OCEAN_SIZE; y++) {
                if (enemyBoard[x][y].equals(" ")) {
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

    private int countUncheckedAdjacent(int x, int y) {
        /**
         * Methot returns number of unchecked squares
         * adjacent to square at X, Y coordinates
         */
        int uncheckedAdjacent = 0;
        if (uncheckedLeft(x, y)) {
            uncheckedAdjacent++;
        }
        if (uncheckedRight(x, y)) {
            uncheckedAdjacent++;
        }
        if (uncheckedBottom(x, y)) {
            uncheckedAdjacent++;
        }
        if (uncheckedTop(x, y)) {
            uncheckedAdjacent++;
        }

        return uncheckedAdjacent;
    }

    public String[][] getEnemyBoard() {
        /**
         * Accessor method for variable enemyBoard
         */
        return enemyBoard;
    }
}