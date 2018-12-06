package cs1302.arcade;

import javafx.scene.control.Button;

public class CheckersBoard
{
    int[][] board;

    public CheckersBoard()
    {
        board = new int[8][8];  //0 = empty, 1 = red, 2 = black, 3 = red king, 4 = black king
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
                    board[r][c] = 1;
                }
            }
        }

        for(int r = 5; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                if(r % 2 == c % 2)
                {
                    board[r][c] = 2;
                }
            }
        }
    }

    public int[][] getBoard()
    {
        return board;
    }

    public static void main(String[] args)
    {
        CheckersBoard board = new CheckersBoard();
        System.out.println(board.getBoard());
    }

} //CheckersBoard
