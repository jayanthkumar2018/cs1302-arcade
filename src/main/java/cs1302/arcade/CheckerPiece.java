package cs1302.arcade;

public class CheckerPiece
{
    private CheckersBoard board;
    private String color;
    private int row, col;
    private boolean king;

    public CheckerPiece(CheckersBoard board, String color, int row, int col)
    {
        this.board = board;
        this.color = color;
        this.row = row;
        this.col = col;
        king = false;
    }

    public boolean canMove()
    {
        if(!king)
        {
            if(color.equals("red"))
            {
                return redCanJump() || redCanMove();
            }
            return blackCanJump() || blackCanMove();
        }
        if(color.equals("red"))
        {
            return kingRedCanJump() || kingRedCanMove();
        }
        return kingBlackCanJump() || kingBlackCanMove();
    }

    private boolean redCanMove()
    {
        if(col == 0)
        {
            if(board.get(row + 1, col + 1) == null)
            {
                return true;
            }
        }
        else if(col == 7)
        {
            if(board.get(row + 1, col - 1) == null)
            {
                return true;
            }
        }
        else if((board.get(row + 1, col - 1) == null || board.get(row + 1, col + 1) == null))
        {
            return true;
        }
        return false;
    }

    private boolean kingRedCanMove()
    {

    }

    private boolean blackCanMove()
    {
        if(col == 0)
        {
            if(board.get(row - 1, col + 1) == null)
            {
                return true;
            }
        }
        else if(col == 7)
        {
            if(board.get(row - 1, col - 1) == null)
            {
                return true;
            }
        }
        else if((board.get(row - 1, col - 1) == null || board.get(row - 1, col + 1) == null))
        {
            return true;
        }
        return false;
    }

    private boolean kingBlackCanMove()
    {

    }

    private boolean redCanJump()
    {
        if(col == 0 || col == 1)
        {
            if(board.get(row + 2, col + 2) == null
                    && board.get(row + 1, col + 1).color.equals("black"))
            {
                return true;
            }
        }
        else if(col == 7 || col == 6)
        {
            if(board.get(row + 2, col - 2) == null
                    && board.get(row + 1, col))
        }
    }

    private boolean kingRedCanJump()
    {

    }

    private boolean blackCanJump()
    {

    }

    private boolean kingBlackCanJump()
    {

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
