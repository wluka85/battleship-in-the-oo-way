
public class OceanValidator {

    private static final int OCEAN_SIZE = 10;
    private static int lastPart;

    public static boolean validateOcean(String[][] ocean, Ship ship) {

        boolean valid = true;

        lastPart = ship.shipLength - 1;
        boolean vertical = ship.getVertical();
        for (int i = 0; i < ship.getShipSize(); i++) {

            int x = getXOfSquare(i);
            int y = getYOfSquare(i);

            if (vertical) {
                valid = checkVerticalSquare(ocean, x, y, i);
            } else {
                valid = checkHorizontalSquare(ocean, x, y, i);
            }
        }

        return valid;

    }

    private static boolean checkVerticalSquare(String[][] ocean, int x, int y, int part) {
        /**
         * Function checks if 'ship' oriented vertically 
         * is placed accordingly to game rules
         * (isn't touching any other ship)
         * 
         * Every part checks Squares above and below
         * Fist and Last part also checks Squares on sides
         */
        
        if (x + 1 < OCEAN_SIZE && ocean[x + 1][y].equals("S")) {
            return false;
        }

        if (x - 1 >= 0 && ocean[x - 1][y].equals("S")) {
            return false;
        }

        if (part = 0 && x-1 >= 0) {
            // If there is Ship on left:
            if (ocean[x - 1][y].equals("S")) {
                return false;
            }

            // If there is Ship above:
            if (y - 1 >= 0 && ocean[x - 1][y - 1].equals("S")) {
                return false;
            }

            // If there is Ship below:
            if (y + 1 < OCEAN_SIZE && ocean[x - 1][y + 1].equals("S")) {
                return false;
            }
        }

        if (part = lastPart && x + 1 < OCEAN_SIZE) {
            // If there is Ship on right:
            if (ocean[x + 1][y].equals("S")) {
                return false;
            }

            // If there is Ship above:
            if (y - 1 >= 0 && ocean[x + 1][y - 1].equals("S")) {
                return false;
            }

            // If there is Ship below:
            if (y + 1 < OCEAN_SIZE && ocean[x + 1][y + 1].equals("S")) {
                return false;
            }
        }
        return true;
    }

}