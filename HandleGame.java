import java.util.ArrayList;
import java.lang.Object;

public class HandleGame {

    private List<Object> players = new ArrayList<>();
    private List<Ocean> oceans = new ArrayList<>();

    public void pvp() {
        oceans.add(new Ocean());
        players.add(new Player(oceans.get(0)));

        oceans.add(new Ocean());
        players.add(new Player(oceans.get(1)));

        playTheGame();
    }

    public void pvAI() {
        oceans.add(new Ocean());
        players.add(new Player(oceans.get(0)));

        oceans.add(new Ocean());
        players.add(new AI(oceans.get(1)));

        playTheGame();
    }

    public void aivAI() {
        oceans.add(new Ocean());
        players.add(new AI(oceans.get(0)));

        oceans.add(new Ocean());
        players.add(new AI(oceans.get(1)));

        playTheGame();
    }

    public void playTheGame() {

        boolean endOfGame = false;

        int turn = 0;
        do {
            Ocean oceanOfEnemy = oceans.get((turn + 1) % 2);
            players.get(turn%2).takeAShot(oceanOfEnemy);
            endOfGame = checkIfGameOver(oceanOfEnemy);
            turn++;

        }while(!endOfGame);
    }

    public String takeAShot(int x, int y, Ocean ocean) {

    }
}