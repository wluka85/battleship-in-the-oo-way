import java.util.List;
import java.util.ArrayList;
import java.lang.Object;

public class HandleGame {

    private List<Object> players = new ArrayList<>();
    private List<Ocean> oceans = new ArrayList<>();

    public HandleGame (int level1, int level2) {
        aivAI(level1, level2);
    }

    public HandleGame (Ocean ocean, int level) {
        pvAI(ocean, level);
    }

    public HandleGame (Ocean ocean1, Ocean ocean2) {
        pvp(ocean1, ocean2);
    }

    private void pvp(Ocean ocean1, Ocean ocean2) {
        oceans.add(ocean1);
        players.add(new Player(oceans.get(0)));

        oceans.add(ocean2);
        players.add(new Player(oceans.get(1)));

    }

    private void pvAI(Ocean ocean, int level) {
        oceans.add(ocean);
        players.add(new Player(oceans.get(0)));

        oceans.add(new Ocean());
        players.add(new AI(oceans.get(1), level));

    }

    private void aivAI(int level1, int level2) {
        oceans.add(new Ocean());
        players.add(new AI(oceans.get(0), level1));

        oceans.add(new Ocean());
        players.add(new AI(oceans.get(1), level2));

    }

    public boolean checkIfGameOver() {
        Ocean ocean1 = oceans.get(0);
        Ocean ocean2 = oceans.get(1);

        return (checkOcean(ocean1) && checkOcean(ocean2));
    }

    private boolean checkOcean(Ocean ocean) {
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
        int nextPlayerIndex = (playerIndex + 1) % 2;
        oceans.get(nextPlayerIndex).takeShot(x, y);
    }

    public void takeATurn(int playerIndex) {
        int nextPlayerIndex = (playerIndex + 1) % 2;
        AI currentPlayer = (AI) players.get(playerIndex);
        currentPlayer.takeAShoot(oceans.get(nextPlayerIndex));
    }

    public String[][] getOceanBoard(int playerIndex, int boardNumber) {
        if (boardNumber == 0) {
            return oceans.get(playerIndex).getOceanBoard();
        } else {
            if (players.get(playerIndex).getClass().equals(AI)) {
                AI currentPlayer = (AI) players.get(playerIndex);
                return currentPlayer.getEnemyBoard();
            } else {
                Player currentPlayer = (Player) players.get(playerIndex);
                return currentPlayer.getEnemyBoard();
            }

        }
    }
}