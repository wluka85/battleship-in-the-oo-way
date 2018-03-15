import java.util.List;
import java.util.ArrayList;

public class Ship {
    /**
     * 'shipParts'
     * - list of Square objects which are part of ship (on board)
     * 
     * 'x'
     * - 'x' coordinate of first ships square on board
     * 
     * 'y'
     * - 'y' coordinate of first ships square on board
     * 
     * 'shipLength'
     * - length of ship (number of squares)
     * 
     * 'vertical'
     * - information if ship is oriented vertically of horizontally
     * 
     * 'healthPoints'
     * - number of health points left
     */

    private List<Square> shipParts = new ArrayList<>();
    private int x;
    private int y;
    private int shipLength;
    private boolean vertical;
    private int healthPoints;

    public Ship(int x, int y, int shipLength) {
        /**
         * constructor for Ship class (available in 2 versions)
         */
        this.x = x;
        this.y = y;
        this.shipLength = shipLength;
        vertical = false;
        healthPoints = shipLength;
        makeShip();
    }

    public Ship(int x, int y, int shipLength, boolean vertical) {
        /**
         * constructor for Ship class (available in 2 versions)
         */
        this.x = x;
        this.y = y;
        this.shipLength = shipLength;
        this.vertical = vertical;
        this.healthPoints = shipLength;
        makeShip();
    }

    private void makeShip() {
        /**
         * Creates Square objects, and adds them to 'shipParts' list
         */
        for (int i = 0; i < shipLength; i++) {
            if (vertical == true)
                shipParts.add(new Square(x, y + i));

            else
                shipParts.add(new Square(x + i, y));
        }
    }

    public int getShipSize() {
        /**
         * Returns size of 'shipParts' list
         */
        return shipParts.size();
    }

    public int getXOfSquare(int indexSquare) {
        /**
         * returns 'x' coordinate of certain part of ship
         */
        return shipParts.get(indexSquare).getX();
    }

    public int getYOfSquare(int indexSquare) {
        /**
         * returns 'y' coordinate of certain part of ship
         */
        return shipParts.get(indexSquare).getY();
    }

    public boolean getVertical() {
        /**
         * Accessor method for variable 'vertical'
         */
        return vertical;
    }

    public void decrementHealthPoints() {
        /**
         * Decreases 'healthPoints' variable (int) by 1
         */
        healthPoints -= 1;
    }

    public int getHealthPoints() {
        /**
         * Accessor method for variable 'healthPoints'
         */
        return healthPoints;
    }

    public boolean checkIfShip(int x, int y) {
        /**
         * Checks if any Ship part (Square) is on X, Y coordinates
         */
        for (int i = 0; i < shipParts.size(); i++) {
            if (getXOfSquare(i) == x && getYOfSquare(i) == y) {
                return true;
            }
        }
        return false;
    }
}
