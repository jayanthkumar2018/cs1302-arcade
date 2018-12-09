package cs1302.arcade;

public class CheckersBoard
{
    CheckerPiece[][] boardVar;

    CheckersBoard()
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

//    CheckerPiece get(int r, int c)
//    {
//        if(boardVar[r][c] != null)
//        {
//            return boardVar[r][c];
//        }
//        return null;
//    }

    public String toString()
    {
        String rtn = "";
        for(int r = 0; r < boardVar.length; r++)
        {
            for(int c = 0; c < boardVar[r].length; c++)
            {
                if(boardVar[r][c] == null)
                {
                    rtn += "0";
                }
                else
                {
                    rtn += boardVar[r][c];
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
        System.out.println(board.boardVar[2][1].canMove());
    }

} //CheckersboardVar
