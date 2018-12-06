package cs1302.arcade;

import javafx.scene.control.Button;

public class CheckersBoard
{
    private CheckerPiece[][] board;

    public CheckersBoard()
    {
        board = new CheckerPiece[8][8];  //0 = empty, 1 = red, 2 = black, 3 = red king, 4 = black king
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
                    board[r][c] = new CheckerPiece(this, "red", r, c);
                }
            }
        }
        for(int r = 5; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                if(r % 2 == c % 2)
                {
                    board[r][c] = new CheckerPiece(this, "black", r, c);
                }
            }
        }
    }

    public CheckerPiece get(int r, int c)
    {
        return board[r][c];
    }

    public String toString()
    {
        String rtn = "";
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[r].length; c++)
            {
                if(board[r][c] == null)
                {
                    rtn += "0";
                }
                else
                {
                    rtn += board[r][c];
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
    }

} //CheckersBoard
