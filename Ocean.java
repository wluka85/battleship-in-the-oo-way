public class Ocean {
    private String[][] ocean = new String[10][10];
    private List<Square> ships = new ArrayList<>();


    public Ocean() {

    }


    public void addShip(Ship ship) {
        ships.add(ship);
    }


    private void addShipToOcean(Ship ship) {
        for (int i = 0; i < ships.getShipSize(); i++) {
            int indexX = ships.getXOfSquare(i);
            int indexY = ships.getYOfSquare(i);
            ocean[indexX][indexY] = "s";
        }
    }
}
