
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

    public void updateEnemyOcean(int x, int y, String shootResult) {
        switch (shootResult) {
        case "Hit":
        case "Hit and sunk":
            enemyOcean[x][y] = "X";
            break;
        case "Miss":
            enemyOcean[x][y] = "O";
            break;
        }
    }
}
