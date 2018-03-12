import java.util.List;
import java.util.ArrayList;


public class Ocean {
    private String[][] ocean = new String[10][10];
    private List<Ship> ships = new ArrayList<>();


    public Ocean() {

    }


    public void addShip(Ship ship) {
        ships.add(ship);
    }


    private void addShipToOcean(Ship ship) {
        for (int i = 0; i < ship.getShipSize(); i++) {
            int indexX = ship.getXOfSquare(i);
            int indexY = ship.getYOfSquare(i);
            ocean[indexX][indexY] = "S";
        }
    }
}
