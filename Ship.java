public class Ship {
    private List<Square> ship = new ArrayList<>();
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
            if (vertical == true) ship.add(new Square(x, y + i));

            else ship.add(new Square(x + i, y));
        }
    }
}
