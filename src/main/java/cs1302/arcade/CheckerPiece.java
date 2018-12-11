package cs1302.arcade;


class CheckerPiece
{
    protected String color;
    protected int row, col;
    protected boolean king;

    CheckerPiece(String color, int row, int col)
    {
        this.color = color;
        this.row = row;
        this.col = col;
        king = false;
    }

    CheckerPiece(String color, int row, int col, boolean king)
    {
        this.color = color;
        this.row = row;
        this.col = col;
        this.king = king;
    }

    public String toString()
    {
        if(color.equals("red"))
        {
            return "1";
        }
        return "2";
    }



}
