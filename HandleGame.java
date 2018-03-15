import java.util.List;
import java.util.ArrayList;
import java.lang.Object;

public class HandleGame {

    private List<Object> players = new ArrayList<>();
    private List<Ocean> oceans = new ArrayList<>();

    public HandleGame(int level1, int level2) {
        /**
         * Starts simulation game
         * AI vs AI
         */
        aivAI(level1, level2);
    }

    public HandleGame(Ocean ocean, int level) {
        /**
         * Starts PvAI game
         * Player vs AI
         */
        pvAI(ocean, level);
    }

    public HandleGame(Ocean ocean1, Ocean ocean2) {
        /**
         * Starts PvP game
         * Player vs Player
         */
        pvp(ocean1, ocean2);
    }

    private void pvp(Ocean ocean1, Ocean ocean2) {
        /**
         * Creates boards for players
         * Player vs Player
         */
        oceans.add(ocean1);
        players.add(new Player(oceans.get(0)));

        oceans.add(ocean2);
        players.add(new Player(oceans.get(1)));

    }

    private void pvAI(Ocean ocean, int level) {
        /**
         * Creates boards for players
         * Player vs AI
         */
        oceans.add(ocean);
        players.add(new Player(oceans.get(0)));

        oceans.add(new Ocean());
        players.add(new AI(oceans.get(1), level));

    }

    private void aivAI(int level1, int level2) {
        /**
         * Creates boards for players
         * AI vs AI
         */
        oceans.add(new Ocean());
        players.add(new AI(oceans.get(0), level1));

        oceans.add(new Ocean());
        players.add(new AI(oceans.get(1), level2));

    }

    public boolean checkIfGameOver() {
        /**
         * Method checks if game has ended
         */
        Ocean ocean1 = oceans.get(0);
        Ocean ocean2 = oceans.get(1);
        return (checkOcean(ocean1) || checkOcean(ocean2));
    }

    private boolean checkOcean(Ocean ocean) {
        /**
         * Checks if there are any undestroyed ships on ocean
         */
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

    public void takeATurn(int playerIndex, int x, int y) {
        /**
         * takeATurn (Player)
         * Player shoots at given coordinates X, Y
         */
        int nextPlayerIndex = (playerIndex + 1) % 2;
        String result = oceans.get(nextPlayerIndex).takeShot(x, y);
        Player currentPlayer = (Player) players.get(playerIndex);
        currentPlayer.updateEnemyOcean(x, y, result);
    }

    public void takeATurn(int playerIndex) {
        /**
         * takeATurn (AI)
         */
        int nextPlayerIndex = (playerIndex + 1) % 2;
        AI currentPlayer = (AI) players.get(playerIndex);
        currentPlayer.takeAShoot(oceans.get(nextPlayerIndex));
    }

    public String[][] getOceanBoard(int playerIndex, int boardNumber) {
        /**
         * Method returns displayable ocean
         * Board numbers:
         * 0 - board containing displayed ships
         * 1 - board with attacked fields marked only
         * 
         */
        if (boardNumber == 0) {
            return oceans.get(playerIndex).getOceanBoard();
        } else {
            if (players.get(playerIndex).getClass().getName().equals("AI")) {
                AI currentPlayer = (AI) players.get(playerIndex);
                return currentPlayer.getEnemyBoard();
            } else {
                Player currentPlayer = (Player) players.get(playerIndex);
                return currentPlayer.getEnemyBoard();
            }

        }
    }

    public void setNameOfPlayer(String newName) {
        /**
         * Method updates name of first player
         * (Used in PvAI only)
         */
        Player player = (Player) players.get(0);
        player.setName(newName);
    }

    public String getHighscores() {
        /**
         * Method returns highscore list as single string
         */
        List<String> highscoreList = Highscore.getDisplayableHighscoreList();
        return String.join("\n", highscoreList);
    }
}
