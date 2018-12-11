package cs1302.arcade;

/**
 * Class that represents a CheckerPiece object for the internal logical mechanics
 * of checkers.
 *
 * @author Caleb Colburn
 * @author Jayanth Kumar
 * @version 1.0
 * @since 12/10/2018
 */
class CheckerPiece
{
    String color;
    int row, col;
    boolean king;

    /**
     * Constructs a CheckerPiece object with the specified color, row, and column
     *
     * @param color color of this CheckerPiece object
     * @param row row of this CheckerPiece object
     * @param col column of this CheckerPiece object
     */
    CheckerPiece(String color, int row, int col)
    {
        this.color = color;
        this.row = row;
        this.col = col;
        king = false;
    }

    /**
     * Constructs a CheckerPiece object with the specified color, row, and column
     *
     * @param color color of this CheckerPiece object
     * @param row row of this CheckerPiece object
     * @param col column of this CheckerPiece object
     * @param king true if this CheckerPiece object is a king, false otherwise
     */
    CheckerPiece(String color, int row, int col, boolean king)
    {
        this.color = color;
        this.row = row;
        this.col = col;
        this.king = king;
    }

    /**
     * Returns a string representation of this CheckerPiece object as either
     * a 1 or a 2, to allow for logical operations on the CheckerPiece based
     * off of its color.
     *
     * @return 1 if this CheckerPiece's color is red, 2, otherwise
     */
    public String toString()
    {
        if(color.equals("red"))
        {
            return "1";
        }
        return "2";
    }
}
