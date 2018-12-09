package cs1302.arcade;

public class CheckersBoard
{
    CheckerPiece[][] boardVar;

    private CheckersBoard()
    {
        boardVar = new CheckerPiece[8][8];  //0 = empty, 1 = red, 2 = black, 3 = red king, 4 = black king
        initArray();
    }

    private void initArray()
    {
        for(int r = 0; r < 3; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                if(r % 2 == c % 2)
                {
                    boardVar[r][c] = new CheckerPiece(this, "red", r, c);
                }
            }
        }
        for(int r = 5; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                if(r % 2 == c % 2)
                {
                    boardVar[r][c] = new CheckerPiece(this, "black", r, c);
                }
            }
        }
    }

    CheckerPiece get(int r, int c)
    {
        return boardVar[r][c];
    }

    public String toString()
    {
        String rtn = "";
        for(CheckerPiece[] e : boardVar)
        {
            for (CheckerPiece piece : e)
            {
                if(piece == null)
                {
                    rtn += "0";
                }
                else
                {
                    rtn += piece;
                }
            }
            rtn += "\n";
        }
        return rtn;
    }

    public static void main(String[] args)
    {
        CheckersBoard board = new CheckersBoard();
        System.out.println(board);
        System.out.println(board.boardVar[2][2].canMove());
    }
}
