package com.codecool.players;

import com.codecool.controller.OceanValidator;
import com.codecool.model.Ocean;
import com.codecool.model.Point;
import com.codecool.model.Ship;
import com.codecool.oceanManagers.ShotResultHandler;

import java.util.Random;

public abstract class AI {
    protected String[][] enemyBoard = new String[Ocean.SIZE][Ocean.SIZE];
    protected Ocean ocean;
    protected boolean huntMode;

    public AI(Ocean ocean) {
        this.ocean = ocean;
        fillEnemyBoard();
        addShipsToOcean();
    }

    public String[][] getEnemyBoard() {
        return enemyBoard;
    }

    public abstract void takeAShoot(Ocean enemyOcean);

    private void fillEnemyBoard() {
        for (int x = 0; x < Ocean.SIZE; x++) {
            for (int y = 0; y < Ocean.SIZE; y++) {
                enemyBoard[x][y] = " ";
            }
        }
    }

    private void addShipsToOcean() {
        addShipOfLength(5);
        addShipOfLength(4);
        addShipOfLength(3);
        addShipOfLength(3);
        addShipOfLength(2);
    }

    private void addShipOfLength(int length) {
        Ship ship;
        boolean validShipLocation;
        do {
            Random generator = new Random();
            int x = generator.nextInt(Ocean.SIZE);
            int y = generator.nextInt(Ocean.SIZE);
            boolean vertical = generator.nextBoolean();
            ship = new Ship(x, y, length, vertical);
            validShipLocation = OceanValidator.validateOcean(ocean.getOceanBoard(), ship);
        } while (!validShipLocation);
        ocean.addShip(ship);
    }

    private Point getRandomPointWithWater() {
        int x, y;
        boolean isWater;
        do {
            Random generator = new Random();
            x = generator.nextInt(Ocean.SIZE);
            y = generator.nextInt(Ocean.SIZE);

            isWater = enemyBoard[x][y].equals(" ") && countUncheckedAdjacent(x, y) != 0;

        } while (!isWater);
        return new Point(x, y);
    }

    protected void attackDamagedShip(Ocean enemyOcean) {
        for (int x = 0; x < Ocean.SIZE; x++) {
            for (int y = 0; y < Ocean.SIZE; y++) {
                if (enemyBoard[x][y].equals("X") && hasUncheckedAdjacent(x, y)) {
                    attackAdjacent(x, y, enemyOcean);
                    return;
                }
            }
        }
    }

    private void handleHit(int x, int y) {

        enemyBoard[x][y] = "X";

        if (x - 1 >= 0 && y - 1 >= 0) {
            enemyBoard[x - 1][y - 1] = "O";
        }

        if (x - 1 >= 0 && y + 1 < Ocean.SIZE) {
            enemyBoard[x - 1][y + 1] = "O";
        }

        if (x + 1 < Ocean.SIZE && y - 1 >= 0) {
            enemyBoard[x + 1][y - 1] = "O";
        }

        if (x + 1 < Ocean.SIZE && y + 1 < Ocean.SIZE) {
            enemyBoard[x + 1][y + 1] = "O";
        }
    }

    private void handleHitAndSunk(int x, int y) {
        handleHit(x, y);
        markTop(x, y);
        markDown(x, y);
        markLeft(x, y);
        markRight(x, y);
    }

    private void markTop(int x, int y) {
        for (; y >= 0; y--) {
            if (!enemyBoard[x][y].equals("X")) {
                enemyBoard[x][y] = "O";
                break;
            }
        }
    }

    private void markDown(int x, int y) {
        for (int i = y; i < Ocean.SIZE; i++) {
            if (!enemyBoard[x][i].equals("X")) {
                enemyBoard[x][i] = "O";
                break;
            }
        }
    }

    private void markLeft(int x, int y) {
        for (int i = x; i >= 0; i--) {
            if (!enemyBoard[i][y].equals("X")) {
                enemyBoard[i][y] = "O";
                break;
            }
        }
    }

    private void markRight(int x, int y) {
        for (int i = x; i < Ocean.SIZE; i++) {
            if (!enemyBoard[i][y].equals("X")) {
                enemyBoard[i][y] = "O";
                break;
            }
        }
    }

    private boolean hasUncheckedAdjacent(int x, int y) {
        return (uncheckedLeft(x, y) || uncheckedRight(x, y) || uncheckedTop(x, y) || uncheckedBottom(x, y));
    }

    private boolean uncheckedLeft(int x, int y) {
        return (x - 1 >= 0 && enemyBoard[x - 1][y].equals(" "));
    }

    private boolean uncheckedRight(int x, int y) {
        return (x + 1 < Ocean.SIZE && enemyBoard[x + 1][y].equals(" "));
    }

    private boolean uncheckedTop(int x, int y) {
        return (y - 1 >= 0 && enemyBoard[x][y - 1].equals(" "));
    }

    private boolean uncheckedBottom(int x, int y) {
        return (y + 1 < Ocean.SIZE && enemyBoard[x][y + 1].equals(" "));
    }

    protected void attackAdjacent(int x, int y, Ocean enemyOcean) {
        int targetX = x;
        int targetY = y;
        if (uncheckedLeft(x, y)) {
            targetX = x - 1;
        } else if (uncheckedRight(x, y)) {
            targetX = x + 1;
        } else if (uncheckedTop(x, y)) {
            targetY = y - 1;
        } else {
            targetY = y + 1;
        }
        String shootResult = enemyOcean.takeShot(targetX, targetY);

        switch (shootResult) {
            case "Hit":
                handleHit(targetX, targetY);
                huntMode = true;
                break;
            case "Hit and sunk":
                handleHitAndSunk(targetX, targetY);
                huntMode = false;
                break;
            case "Miss":
                enemyBoard[targetX][targetY] = "O";
                break;
        }
    }

    protected int countUncheckedAdjacent(int x, int y) {
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

    protected void standardShot(Ocean enemyOcean) {
        Point shootPoint = getRandomPointWithWater();

        int x = shootPoint.getX();
        int y = shootPoint.getY();

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
}