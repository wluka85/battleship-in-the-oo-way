import java.util.List;
import java.util.ArrayList;
import java.lang.Object;

public class HandleGame {

    private List<Object> players = new ArrayList<>();
    private List<Ocean> oceans = new ArrayList<>();

    public HandleGame () {
        aivAI();
    }

    public HandleGame (Ocean ocean) {
        pvAI(ocean);
    }

    public HandleGame (Ocean ocean1, Ocean ocean2) {
        pvp(ocea1, ocean2);
    }

    public void pvp(Ocean ocean1, Ocean ocean2) {
        oceans.add(ocean1);
        players.add(new Player(oceans.get(0)));

        oceans.add(ocean2);
        players.add(new Player(oceans.get(1)));

    }

    public void pvAI(Ocean ocean) {
        oceans.add(ocean);
        players.add(new Player(oceans.get(0)));

        oceans.add(new Ocean());
        players.add(new AI(oceans.get(1)));

    }

    public void aivAI() {
        oceans.add(new Ocean());
        players.add(new AI(oceans.get(0)));

        oceans.add(new Ocean());
        players.add(new AI(oceans.get(1)));

    }

    private boolean checkIfGameOver(Ocean ocean) {
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
}