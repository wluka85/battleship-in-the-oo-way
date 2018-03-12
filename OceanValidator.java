
public class OceanValidator {

    public static boolean validateOcean(String[][] ocean, Ship ship) {

        boolean valid = true;

        boolean vertical = ship.getVertical();
        for (int i = 0; i < ship.getShipSize(); i++) {

            int x = getXOfSquare(i);
            int y = getYOfSquare(i);

            if (vertical) {
                valid = checkVerticalSquare(ocean, x, y, i);
            } else {
                valid = checkHorizontalSquare
            }
        }

        return valid;

    }

}