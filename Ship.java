public class Ship {
    private List<Square> shipParts = new ArrayList<>();
    private int x;
    private int y;
    private int shipLength;
    private boolean vertical;


    public Ship(int x, int y, int shipLength) {
        this.x = x;
        this.y = y;
        this.shipLength = shipLength;
        vertical = false;
        makeShip();
    }


    public Ship(int x, int y, int shipLength, boolean vertical) {
        this.x = x;
        this.y = y;
        this.shipLength = shipLength;
        this.vertical = vertical;
        makeShip();
    }


    private makeShip() {
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
}
