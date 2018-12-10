package cs1302.arcade;

class CheckerPiece
{
    private CheckersBoard board;
    private String color;
    private int row, col;
    private boolean king;

    CheckerPiece(CheckersBoard board, String color, int row, int col)
    {
        this.board = board;
        this.color = color;
        this.row = row;
        this.col = col;
        king = false;
    }

    boolean canMove()
    {
        if(board.get(row, col) == null)
        {
            return false;
        }
        else if(!king)
        {
            if(color.equals("red"))
            {
                return redCanJump() || redCanMove();
            }
            return blackCanJump() || blackCanMove();
        }
        if(color.equals("red"))
        {
            return kingRedCanJump() || redCanJump()|| kingCanMove();
        }
        return kingBlackCanJump() || blackCanJump() || kingCanMove();
    }

    private boolean redCanMove()
    {
        if(col == 0)
        {
            return board.get(row + 1, col + 1) == null;
        }
        else if(col == 7)
        {
            return board.get(row + 1, col - 1) == null;
        }
        else
        {
            return board.get(row + 1, col - 1) == null
                    || board.get(row + 1, col + 1) == null;
        }
    }

    private boolean kingCanMove()
    {
        return (redCanMove() && row != 7) || (blackCanMove() && row != 0);
    }

    private boolean blackCanMove()
    {
        if(col == 0)
        {
            return board.get(row - 1, col + 1) == null;
        }
        else if(col == 7)
        {
            return board.get(row - 1, col - 1) == null;
        }
        else {
            return board.get(row - 1, col - 1) == null
                    || board.get(row - 1, col + 1) == null;
        }
    }

    private boolean redCanJump()
    {
        if (row >= 6) {return false;}
        if(col == 0 || col == 1)
        {
            return board.get(row + 2, col + 2) == null
                    && (isNotNull(row + 1, col + 1) &&
                    board.get(row + 1, col + 1).color.equals("black"));
        }
        else if(col == 7 || col == 6)
        {
            return board.get(row + 2, col - 2) == null
                    && (isNotNull(row + 1, col - 1)
                    && board.get(row + 1, col - 1).color.equals("black"));
        }
        else
        {
            return (board.boardVar[row + 2][col + 2] == null && (isNotNull(row + 1, col + 1)) &&
                    board.boardVar[row + 1][col + 1].color.equals("black")) ||
                    (board.boardVar[row + 2][col - 2] == null && (isNotNull(row + 1, col - 1)
                            && board.boardVar[row + 1][col - 1].color.equals("black")));
        }
    }

    private boolean kingRedCanJump()
    {
        if (row <= 1) {return false;}
        if(col == 0 || col == 1)
        {
            return board.get(row - 2, col + 2) == null &&
                    (isNotNull(row - 1, col + 1) && board.get(row - 1, col + 1).color.equals("black"));
        }
        else if(col == 7 || col == 6)
        {
            return board.get(row - 2, col - 2) == null &&
                    (isNotNull(row - 1, col - 1) && board.get(row - 1, col - 1).color.equals("black"));
        }
        else
        {
            return (board.boardVar[row + 2][col + 2] == null && (isNotNull(row + 1, col + 1)) &&
                    board.boardVar[row + 1][col + 1].color.equals("black")) ||
                    (board.boardVar[row + 2][col - 2] == null && (isNotNull(row + 1, col - 1)
                            && board.boardVar[row + 1][col - 1].color.equals("black")));
        }
    }

    private boolean blackCanJump()
    {
        if (row <= 1) {return false;}
        if(col == 0 || col == 1)
        {
            return board.get(row - 2, col + 2) == null &&
                    (isNotNull(row - 1, col + 1) && board.get(row - 1, col + 1).color.equals("red"));
        }
        else if(col == 7 || col == 6)
        {
            return board.get(row - 2, col - 2) == null &&
                    (isNotNull(row - 1, col - 1) && board.get(row - 1, col - 1).color.equals("red"));
        }
        else {
            return (board.boardVar[row + 2][col + 2] == null && (isNotNull(row + 1, col + 1)) &&
                    board.boardVar[row + 1][col + 1].color.equals("red")) ||
                    (board.boardVar[row + 2][col - 2] == null && (isNotNull(row + 1, col - 1)
                            && board.boardVar[row + 1][col - 1].color.equals("red")));
        }
    }

    private boolean kingBlackCanJump()
    {
        if (row >= 6) {return false;}
        if(col == 0 || col == 1)
        {
            return board.get(row + 2, col + 2) == null
                    && (isNotNull(row + 1, col + 1) &&
                    board.get(row + 1, col + 1).color.equals("red"));
        }
        else if(col == 7 || col == 6)
        {
            return board.get(row + 2, col - 2) == null
                    && (isNotNull(row + 1, col - 1)
                    && board.get(row + 1, col - 1).color.equals("red"));
        }
        else {
            return (board.boardVar[row + 2][col + 2] == null && (isNotNull(row + 1, col + 1)) &&
                    board.boardVar[row + 1][col + 1].color.equals("red")) ||
                    (board.boardVar[row + 2][col - 2] == null && (isNotNull(row + 1, col - 1)
                            && board.boardVar[row + 1][col - 1].color.equals("red")));
        }
    }

    private boolean isNotNull(int row, int col)
    {
        return board.boardVar[row][col] != null;
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
