/**
 * Square class module
 * 
 *'x'
 *  data: int
 *  description: 'x' coordinate of square on board
 *
 *'y'
 *  data: int
 *  description: 'y' coordinate of square on board
 */

public class Square {
    private int x;
    private int y;


    public Square(int x, int y) {
    /**
     * Constructor for Square object
     */
        this.x = x;
        this.y = y;
    }


    public int getX() {
    /**
     * Accessor method for 'x' variable
     */
        return x;
    }


    public int getY() {
    /**
     * Accessor method for 'y' variable
     */
        return y;
    }
}
