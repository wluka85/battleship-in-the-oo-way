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
    }

    public void pvAI() {
        oceans.add(new Ocean());
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

    public String takeAShot(int x, int y, Ocean ocean) {

    }
}