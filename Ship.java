public class ships {
    private List<Square> ships = new ArrayList<>();
    private int x;
    private int y;
    private int shipLength;
    private boolean vertical;


    public ships(int x, int y, int shipLength) {
        this.x = x;
        this.y = y;
        this.shipLength = shipLength;
        vertical = false;
        makeShip();
    }


    public ships(int x, int y, int shipLength, boolean vertical) {
        this.x = x;
        this.y = y;
        this.shipLength = shipLength;
        this.vertical = vertical;
        makeShip();
    }


    private makeShip() {
        for (int i = 0; i < shipLength; i++) {
            if (vertical == true) ships.add(new Square(x, y + i));

            else ships.add(new Square(x + i, y));
        }
    }


    public int getShipSize() {
        return ships.size();
    }


    public int getXOfSquare(int indexSquare) {
        return ships.get(indexSquare).getX();
    }


    public int getYOfSquare(int indexSquare) {
        return ships.get(indexSquare).getY();
    }
}
