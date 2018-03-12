import java.util.List;
import java.util.ArrayList;


public class Ship {
    private List<Square> shipParts = new ArrayList<>();
    private int x;
    private int y;
    private int shipLength;
    private boolean vertical;
    private int healthPressure;


    public Ship(int x, int y, int shipLength) {
        this.x = x;
        this.y = y;
        this.shipLength = shipLength;
        vertical = false;
        healthPressure = shipLength;
        makeShip();
    }


    public Ship(int x, int y, int shipLength, boolean vertical) {
        this.x = x;
        this.y = y;
        this.shipLength = shipLength;
        this.vertical = vertical;
        makeShip();
    }


    private void makeShip() {
        for (int i = 0; i < shipLength; i++) {
            if (vertical == true) shipParts.add(new Square(x, y + i));

            else shipParts.add(new Square(x + i, y));
        }
    }


    public int getShipSize() {
        return shipParts.size();
    }


    public int getXOfSquare(int indexSquare) {
        return shipParts.get(indexSquare).getX();
    }


    public int getYOfSquare(int indexSquare) {
        return shipParts.get(indexSquare).getY();
    }


    public boolean getVertical() {
        return vertical;
    }


    public void decrementHealthPressure() {
      healthPressure -= 1;
    }


    public int getHealthPressure() {
      return healthPressure;
    }


    public boolean checkIfShip(int x, int y) {
      for (int i = 0; i < shipParts.size(); i++) {
        if (getXOfSquare(i) == x && getYOfSquare(i) == y) {
          return true;
        }
      }
      return false;
    }
}
