package com.codecool.players;

import com.codecool.model.Ocean;
import com.codecool.model.Point;
import com.codecool.model.Ship;
import com.codecool.oceanManagers.OceanFiller;
import com.codecool.oceanManagers.OceanValidator;
import com.codecool.oceanManagers.ShotResultHandler;

import java.util.Random;

public abstract class AI {
    protected String[][] enemyBoard = new String[Ocean.SIZE][Ocean.SIZE];
    protected Ocean ocean;
    protected ShotResultHandler shotResultHandler;
    protected boolean huntMode;

    public AI(Ocean ocean) {
        this.ocean = ocean;
        OceanFiller.fillEnemyBoard(enemyBoard);
        addShipsToOcean();
        shotResultHandler = new ShotResultHandler(enemyBoard);
    }

    public String[][] getEnemyBoard() {
        return enemyBoard;
    }

    public abstract void takeAShoot(Ocean enemyOcean);

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
        if (shootResult.equals("Hit")) {
            huntMode = true;
        } else if (shootResult.equals("Hit and sunk")) {
            huntMode = false;
        }

        shotResultHandler.smartUpdateEnemyOcean(targetX, targetY, shootResult);
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
        if (shootResult.equals("Hit")) {
            huntMode = true;
        } else if (shootResult.equals("Hit and sunk")) {
            huntMode = false;
        }

        shotResultHandler.smartUpdateEnemyOcean(x, y, shootResult);
    }
}