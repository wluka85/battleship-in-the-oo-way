
public class Player {

    private String[][] enemyOcean = new String[10][10];
    public Ocean ocean;
    private int turns = 0;
    private String playerName = "MentorBot";

    public Player(Ocean ocean) {
        /**
         * Constructor for Player objects
         */
        this.ocean = ocean;
    }

    public String[][] getEnemyBoard() {
        /**
         * Accessor method for variable 'enemyOcean'
         */
        return enemyOcean;
    }

    public void updateEnemyOcean(int x, int y, String shootResult) {
        /**
         * Method updates 'enemyOcean' accordingly to shootResult
         */
        switch (shootResult) {
        case "Hit":
        case "Hit and sunk":
            enemyOcean[x][y] = "X";
            break;
        case "Miss":
            enemyOcean[x][y] = "O";
            break;
        }
        turns++;
    }

    public void setName(String newName) {
        /**
         * Method used to change 'playerName' variable
         */
        this.playerName = newName;;
    }

    public String getName(){
        /**
         * Accessor method for variable 'playerName'
         */
        return this.playerName;
    }
}