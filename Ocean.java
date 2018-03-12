import java.util.List;
import java.util.ArrayList;


public class Ocean {
    private String[][] ocean = new String[10][10];
    private List<Ship> ships = new ArrayList<>();


    public Ocean() {
      for (int i = 0; i < ocean.length; i++) {
        for (int j = 0; j < ocean[i].length; j++) {
          ocean[i][j] = " ";
        }
      }
    }


    public void addShip(Ship ship) {
        ships.add(ship);
        addShipToOcean(ship);
    }


    private void addShipToOcean(Ship ship) {
        for (int i = 0; i < ship.getShipSize(); i++) {
            int indexX = ship.getXOfSquare(i);
            int indexY = ship.getYOfSquare(i);
            ocean[indexX][indexY] = "S";
        }
    }


    public String takeShot(int x, int y) {
      if (ocean[x][y].equals("S")) {
        ocean[x][y] = "X";
        int hitedShip = -1;
        for (int i = 0; i < ships.size(); i++) {
          if (ships.get(i).checkIfShip(x, y)) {
            ships.get(i).decrementHealthPressure();
            hitedShip = i;
          }
        }
        if (ships.get(hitedShip).getHealthPressure() < 1) {
          return "Hit and sunk";
        } else {
          return "Hit";
        }
      } else {
        ocean[x][y] = "O";
        return "Miss";
      }
    }
}
