import java.util.Random;

public class AI {

    private static final int OCEAN_SIZE = 10;
    private String[][] enemyOcean = new String[10][10];
    public Ocean ocean;
    private int level;

    public AI (Ocean ocean, int level) {
        this.ocean = ocean;
        this.level = level;
        addShipsToOcean();
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

    public void takeATurn() {
        if (level == 1) {
            easyAIMove();
        }
    }

    private void easyAIMove() {

        boolean unknownSquare;
        do {
            Random generator = new Random();
            int x = generator.nextInt(OCEAN_SIZE);
            int y = generator.nextInt(OCEAN_SIZE);

            unknownSquare = board[x][y].equals(" ");

        }while(!unknownSquare);


    }
}