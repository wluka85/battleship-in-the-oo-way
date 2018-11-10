package com.codecool.oceanManagers;

import com.codecool.model.Ocean;
import com.codecool.model.Ship;

public class OceanValidator {
    private static int lastPart;

    public static boolean validateOcean(String[][] ocean, Ship ship) {

        boolean valid = true;

        lastPart = ship.getShipSize() - 1;
        boolean vertical = ship.getVertical();
        for (int i = 0; i < ship.getShipSize(); i++) {

            int x = ship.getXOfSquare(i);
            int y = ship.getYOfSquare(i);

            if (valid) {
                if (vertical) {
                    valid = checkVerticalSquare(ocean, x, y, i);
                } else {
                    valid = checkHorizontalSquare(ocean, x, y, i);
                }
            }
        }

        return valid;

    }

    private static boolean checkHorizontalSquare(String[][] ocean, int x, int y, int part) {
        /*
          Function checks if 'ship' oriented vertically
          is placed accordingly to game rules
          (isn't touching any other ship)

          Every part checks Squares above, below and on its x,y
          Fist and Last part also checks Squares on sides

          First part checks also if entire ship fits on Ocean (board)
         */

        if (y + 1 < Ocean.SIZE && ocean[x][y + 1].equals("S")) {
            return false;
        }

        if (y - 1 >= 0 && ocean[x][y - 1].equals("S")) {
            return false;
        }

        if (ocean[x][y].equals("S")) {
            return false;
        }

        if (part == 0 && x - 1 >= 0) {
            // Check if ship is not too long for this location:
            if (x + lastPart >= Ocean.SIZE) {
                return false;
            }

            // If there is Ship on left:
            if (ocean[x - 1][y].equals("S")) {
                return false;
            }

            // If there is Ship above:
            if (y - 1 >= 0 && ocean[x - 1][y - 1].equals("S")) {
                return false;
            }

            // If there is Ship below:
            if (y + 1 < Ocean.SIZE && ocean[x - 1][y + 1].equals("S")) {
                return false;
            }
        }

        if (part == lastPart && x + 1 < Ocean.SIZE) {
            // If there is Ship on right:
            if (ocean[x + 1][y].equals("S")) {
                return false;
            }

            // If there is Ship above:
            if (y - 1 >= 0 && ocean[x + 1][y - 1].equals("S")) {
                return false;
            }

            // If there is Ship below:
            return y + 1 >= Ocean.SIZE || !ocean[x + 1][y + 1].equals("S");
        }
        return true;
    }

    private static boolean checkVerticalSquare(String[][] ocean, int x, int y, int part) {
        /*
          Function checks if 'ship' oriented horizontally
          is placed accordingly to game rules
          (isn't touching any other ship)

          Every part checks Squares on sides (left & right) and on its x,y
          Fist and Last part also checks Squares above and below

          First part checks also if entire ship fits on Ocean (board)
         */

        if (x + 1 < Ocean.SIZE && ocean[x + 1][y].equals("S")) {
            return false;
        }

        if (x - 1 >= 0 && ocean[x - 1][y].equals("S")) {
            return false;
        }

        if (ocean[x][y].equals("S")) {
            return false;
        }

        if (part == 0 && y - 1 >= 0) {
            // Check if ship is not too long for this location:
            if (y + lastPart >= Ocean.SIZE) {
                return false;
            }

            // If there is Ship on top-left:
            if (x - 1 >= 0 && ocean[x - 1][y - 1].equals("S")) {
                return false;
            }

            // If there is Ship above:
            if (ocean[x][y - 1].equals("S")) {
                return false;
            }

            // If there is Ship on top-right:
            if (x + 1 < Ocean.SIZE && ocean[x + 1][y - 1].equals("S")) {
                return false;
            }
        }

        if (part == lastPart && y + 1 < Ocean.SIZE) {
            // If there is Ship on bottom-left:
            if (x - 1 >= 0 && ocean[x - 1][y + 1].equals("S")) {
                return false;
            }

            // If there is Ship below:
            if (ocean[x][y + 1].equals("S")) {
                return false;
            }

            // If there is Ship on bottom-right:
            return x + 1 >= Ocean.SIZE || !ocean[x + 1][y + 1].equals("S");
        }

        return true;
    }
}
