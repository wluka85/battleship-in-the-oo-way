import java.util.Random;

public class AI {

    private static final int OCEAN_SIZE = 10;
    private String[][] enemyBoard = new String[10][10];
    public Ocean ocean;
    private int level;
    private boolean huntMode = false;
    // huntMode is used only by AI on level 3

    public AI (Ocean ocean, int level) {
        this.ocean = ocean;
        this.level = level;
        fillEnemyBoard();
        addShipsToOcean();
    }

    private void fillEnemyBoard() {
        for (int x = 0; x < OCEAN_SIZE; x++) {
            for (int y = 0; y < OCEAN_SIZE; y++) {
                enemyBoard[x][y] = " ";
            }
        }
    }

    private void addShipsToOcean() {
        addCarrier();
        addBattleship();
        addCruiser();
        addCruiser();
        addDestroyer();

    }

    private void addCarrier() {
        Ship carrier;
        boolean validShipLocation;
        do {
            Random generator = new Random();
            int x = generator.nextInt(OCEAN_SIZE);
            int y = generator.nextInt(OCEAN_SIZE);
            carrier = new Ship(x, y, 5);
            validShipLocation = OceanValidator.validateOcean(ocean.getOceanBoard(), carrier);
        } while (!validShipLocation);
        ocean.addShip(carrier);
    }

    private void addBattleship() {
        Ship battleship;
        boolean validShipLocation;
        do {
            Random generator = new Random();
            int x = generator.nextInt(OCEAN_SIZE);
            int y = generator.nextInt(OCEAN_SIZE);
            battleship = new Ship(x, y, 4);
            validShipLocation = OceanValidator.validateOcean(ocean.getOceanBoard(), battleship);
        } while (!validShipLocation);
        ocean.addShip(battleship);
    }

    private void addCruiser() {
        Ship cruiser;
        boolean validShipLocation;
        do {
            Random generator = new Random();
            int x = generator.nextInt(OCEAN_SIZE);
            int y = generator.nextInt(OCEAN_SIZE);
            cruiser = new Ship(x, y, 3);
            validShipLocation = OceanValidator.validateOcean(ocean.getOceanBoard(), cruiser);
        } while (!validShipLocation);
        ocean.addShip(cruiser);
    }

    private void addDestroyer() {
        Ship destroyer;
        boolean validShipLocation;
        do {
            Random generator = new Random();
            int x = generator.nextInt(OCEAN_SIZE);
            int y = generator.nextInt(OCEAN_SIZE);
            destroyer = new Ship(x, y, 2);
            validShipLocation = OceanValidator.validateOcean(ocean.getOceanBoard(), destroyer);
        } while (!validShipLocation);
        ocean.addShip(destroyer);
    }

    public void takeATurn(Ocean enemyOcean) {
        if (level == 1) {
            easyAIMove(enemyOcean);
        } else if (level == 2) {
            mediumAIMove(enemyOcean);
        } else if (level == 3) {
            hardAIMove(enemOcean);
        }
    }

    private void easyAIMove(Ocean enemyOcean) {

        int x, y;
        boolean isWater;
        do {
            Random generator = new Random();
            x = generator.nextInt(OCEAN_SIZE);
            y = generator.nextInt(OCEAN_SIZE);

            isWater = enemyBoard[x][y].equals(" ");

        }while(!isWater);

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
        int x, y;
        boolean isWater;
        do {
            Random generator = new Random();
            x = generator.nextInt(OCEAN_SIZE);
            y = generator.nextInt(OCEAN_SIZE);

            isWater = enemyBoard[x][y].equals(" ");

        }while(!isWater);

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

        if (huntMode) {
            attackDamagedShip(enemyOcean);
        } else {
            mediumAIMove(enemyOcean);
        }
    }

    private void handleHit(int x, int y) {

        enemyBoard[x][y] = "X";

        if (x - 1 >= 0 && y - 1 >= 0) {
            enemyBoard[x-1][y-1] = "O";
        }

        if (x - 1 >= 0 && y + 1 < OCEAN_SIZE) {
            enemyBoard[x-1][y+1] = "O";
        }

        if (x + 1 < OCEAN_SIZE && y - 1 >= 0) {
            enemyBoard[x+1][y-1] = "O";
        }

        if (x + 1 < OCEAN_SIZE && y + 1 < OCEAN_SIZE) {
            enemyBoard[x+1][y+1] = "O";
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
        for (int i = y; i >= 0; i--) {
            if (enemyBoard[x][i].equals("X")){
                continue;
            } else {
                enemyBoard[x][i] = "O";
                break;
            }
        }
    }

    private void markDown(int x, int y) {
        for (int i = y; i < OCEAN_SIZE; i++) {
            if (enemyBoard[x][i].equals("X")){
                continue;
            } else {
                enemyBoard[x][i] = "O";
                break;
            }
        }
    }

    private void markLeft(int x, int y) {
        for (int i = x; i >= 0; i--) {
            if (enemyBoard[i][y].equals("X")){
                continue;
            } else {
                enemyBoard[i][y] = "O";
                break;
            }
        }
    }

    private void markRight(int x, int y) {
        for (int i = x; i < OCEAN_SIZE; i++) {
            if (enemyBoard[i][y].equals("X")){
                continue;
            } else {
                enemyBoard[i][y] = "O";
                break;
            }
        }
    }
}