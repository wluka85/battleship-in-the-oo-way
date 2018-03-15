
public class Player {

    private static final int OCEAN_SIZE = 10;
    private String[][] enemyOcean = new String[10][10];
    public Ocean ocean;

    public Player(Ocean ocean) {
        this.ocean = ocean;
    }

    public String[][] getEnemyBoard() {
        return enemyOcean;
    }
}
