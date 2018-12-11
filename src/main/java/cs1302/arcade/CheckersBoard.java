package cs1302.arcade;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class CheckersBoard extends Application
{
    private CheckerPiece[][] boardVar;
    private static final boolean RED_PLAYER = true;
    private static final boolean GREEN_PLAYER = false;
    private boolean currentPlayer = RED_PLAYER;
    private boolean gameInProgress = true;
    private CheckerPiece selectedPiece;
    private ArrayList<CheckerPiece> allBlackPieces = new ArrayList<>();
    private ArrayList<CheckerPiece> allRedPieces = new ArrayList<>();
    private Label message = new Label("Welcome!");
    private Button forfeitButton = new Button("Forfeit");


    @Override
    public void start(Stage primaryStage)
    {
        Stage stage = primaryStage;
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        StackPane mainContainer = new StackPane();
        GridPane checkerPattern = makePane();
        GridPane checkerDisplay = makeCheckerDisplay();
        StackPane gameBoard = new StackPane();
        gameBoard.getChildren().addAll(checkerPattern, checkerDisplay);
        vBox.getChildren().addAll(forfeitButton, message);
        hBox.getChildren().addAll(gameBoard, vBox);
        checkerDisplay.setPickOnBounds(false);
        checkerDisplay.setMouseTransparent(true);

        Scene scene = new Scene(hBox, 640, 480);
        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();

    }

    private GridPane makeCheckerDisplay()
    {
        GridPane checkerDisplay = new GridPane();
        for(int row = 0; row < 8; row++)
        {
            for(int col = 0; col < 8; col++)
            {
                if(boardVar[row][col] != null)
                {
                    Circle circle = new Circle();
                    circle.setRadius(30);
                    if(boardVar[row][col].color.equals("red"))
                    {
                        circle.setFill(Color.RED);
                    }
                    else
                    {
                        circle.setFill(Color.GREEN);
                    }
                    GridPane.setRowIndex(circle, row);
                    GridPane.setColumnIndex(circle, col);
                    checkerDisplay.getChildren().addAll(circle);
                    circle.setPickOnBounds(false);
                }
                else
                {
                    Circle circle = new Circle();
                    circle.setRadius(30);
                    circle.setFill(Color.TRANSPARENT);
                    GridPane.setRowIndex(circle, row);
                    GridPane.setColumnIndex(circle, col);
                    checkerDisplay.getChildren().addAll(circle);
                    circle.setPickOnBounds(false);
                }
            }
        }
        return checkerDisplay;
    }

    private Pane makeVictoryScreen()
    {
        return new Pane();
    }

    private GridPane makePane()
    {
        GridPane pane = new GridPane();
        for(int row = 0; row < 8; row++)
        {
            for(int col = 0; col < 8; col++)
            {
                Rectangle rec = new Rectangle();
                rec.setWidth(60);
                rec.setHeight(60);
                if(row % 2 == col % 2)
                {
                    rec.setFill(Color.WHITE);
                }
                else
                {
                    rec.setFill(Color.BLACK);
                }
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                pane.getChildren().addAll(rec);
                rec.setOnMouseClicked(e -> {
                    CheckerPiece piece;

                    if(boardVar[GridPane.getRowIndex(rec)][GridPane.getColumnIndex(rec)] != null)
                    {
                        piece = boardVar[GridPane.getRowIndex(rec)][GridPane.getColumnIndex(rec)];
                        if(currentPlayer == RED_PLAYER && piece.color.equals("red") ||
                                currentPlayer == GREEN_PLAYER && piece.color.equals("black"))
                        {
                            selectedPiece = piece;
                        }
                        else
                        {
                            message.setText("NOT YOUR TURN");
                        }
                    }
                    else
                    {
                        if(selectedPiece != null)
                        {
                            doMove(selectedPiece, GridPane.getRowIndex(rec), GridPane.getColumnIndex(rec));
                        }
                    }
                });
            }
        }
        return pane;
    }

    private void doMove(CheckerPiece piece, int targetRow, int targetCol)
    {
        if(!canMove(piece))
        {
            message.setText("This piece has no legal moves");
            selectedPiece = null;
            return;
        }
        if(piece.king)
        {
            if(kingJump(piece, targetRow, targetCol))
            {
                return;
            }
        }
        else
        {
            if(pieceJump(piece, targetRow, targetCol))
            {
                return;
            }
        }

        if(piece.color.equals("red"))
        {
            for(CheckerPiece eachChecker: allRedPieces)
            {
                if(eachChecker.king)
                {
                    if(kingRedCanJump(eachChecker))
                    {
                        message.setText("One of your other pieces can capture an enemy " +
                                "piece, so this move is invalid");
                    }
                }
                else
                {
                    if(redCanJump(eachChecker))
                    {
                        message.setText("One of your other pieces can capture an enemy " +
                                "piece, so this move is invalid");
                    }
                }
            }
        }
        else
        {
            for(CheckerPiece eachChecker: allBlackPieces)
            {
                if(eachChecker.king)
                {
                    if(kingRedCanJump(eachChecker))
                    {
                        message.setText("One of your other pieces can capture an enemy " +
                                "piece, so this move is invalid");
                    }
                }
                else
                {
                    if(redCanJump(eachChecker))
                    {
                        message.setText("One of your other pieces can capture an enemy " +
                                "piece, so this move is invalid");
                    }
                }
            }
        }
        if(piece.king)
        {
            if(kingMove(piece, targetRow, targetCol))
            {
                return;
            }
        }
        else
        {
            if(pieceMove(piece, targetRow, targetCol)) {
                return;
            }
        }
        message.setText("This move is invalid!");
        selectedPiece = null;
    }

    private boolean kingJump(CheckerPiece piece, int targetRow, int targetCol)
    //return true if successful
    {
        if((targetRow == piece.row+2 && targetCol == piece.col+2) ||
                (targetRow == piece.row+2 && targetCol == piece.col-2) ||
                (targetRow == piece.row-2 && targetCol == piece.col-2) ||
                (targetRow == piece.row-2 && targetCol == piece.col+2))
        {
            if(boardVar[(piece.row + targetRow)/2][(piece.col + targetCol)/2] != null &&
                    !boardVar[(piece.row + targetRow)/2][(piece.col + targetCol)/2]
                            .color.equals(piece.color))
            {
                CheckerPiece temp = new CheckerPiece(piece.color, targetRow,
                        targetCol, piece.king);
                boardVar[piece.row][piece.col] = null;
                boardVar[(piece.row + targetRow)/2][(piece.col + targetCol)/2] = null;
                boardVar[targetRow][targetCol] = temp;
                selectedPiece = null;
                return true;
            }
        }
        return false;
    }

    private boolean pieceJump(CheckerPiece piece, int targetRow, int targetCol)
    //assumes target space is in bounds and empty, otherwise does nothing
    {
        if(piece.color.equals("red"))
        {
            if((targetRow == piece.row+2 && (targetCol == piece.col+2
                    || targetCol == piece.col-2)))
            {
                if(boardVar[(piece.row + targetRow)/2][(piece.col + targetCol)/2] != null &&
                        !boardVar[(piece.row + targetRow)/2][(piece.col + targetCol)/2]
                                .color.equals(piece.color))
                {
                    CheckerPiece temp = new CheckerPiece(piece.color, targetRow,
                            targetCol, piece.king);
                    boardVar[piece.row][piece.col] = null;
                    boardVar[(piece.row + targetRow)/2][(piece.col + targetCol)/2] = null;
                    boardVar[targetRow][targetCol] = temp;
                    selectedPiece = null;
                    return true;
                }
            }
        }
        else
        {
            if((targetRow == piece.row-2 && (targetCol == piece.col-2
                    || targetCol == piece.col+2)))
            {
                if(boardVar[(piece.row + targetRow)/2][(piece.col + targetCol)/2] != null &&
                        !boardVar[(piece.row + targetRow)/2][(piece.col + targetCol)/2]
                                .color.equals(piece.color))
                {
                    CheckerPiece temp = new CheckerPiece(piece.color, targetRow,
                            targetCol, piece.king);
                    boardVar[piece.row][piece.col] = null;
                    boardVar[(piece.row + targetRow)/2][(piece.col + targetCol)/2] = null;
                    boardVar[targetRow][targetCol] = temp;
                    selectedPiece = null;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean kingMove(CheckerPiece piece, int targetRow, int targetCol)
    //assumes target space is in bounds and empty, otherwise does nothing
    {
        if((targetRow == piece.row+1 && targetCol == piece.col+1) ||
                (targetRow == piece.row+1 && targetCol == piece.col-1) ||
                (targetRow == piece.row-1 && targetCol == piece.col-1) ||
                (targetRow == piece.row-1 && targetCol == piece.col+1))
        {
            CheckerPiece temp = new CheckerPiece(piece.color, targetRow, targetCol, piece.king);
            boardVar[piece.row][piece.col] = null;
            boardVar[targetRow][targetCol] = temp;
            selectedPiece = null;
            return true;
        }
        return false;
    }

    private boolean pieceMove(CheckerPiece piece, int targetRow, int targetCol)
    //assumes target space is in bounds and empty, otherwise does nothing
    {
        if(piece.color.equals("red"))
        {
            if(!(targetRow == piece.row+1 && (targetCol == piece.col+1) || targetCol == piece.col-1))
            {
                message.setText("Invalid move");
                selectedPiece = null;
                return false;
            }
        }
        else
        {
            if(!(targetRow == piece.row-1 && (targetCol == piece.col-1)
                    || targetCol == piece.col+1))
            {
                message.setText("Invalid move");
                selectedPiece = null;
                return false;
            }
        }
            CheckerPiece temp = new CheckerPiece(piece.color, targetRow, targetCol, piece.king);
            boardVar[piece.row][piece.col] = null;
            boardVar[targetRow][targetCol] = temp;
            selectedPiece = null;
            return true;
    }

    private void initArray()
    {
        for(int r = 0; r < 3; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                if(r % 2 == c % 2)
                {
                    boardVar[r][c] = new CheckerPiece("red", r, c);
                    allRedPieces.add(boardVar[r][c]);
                }
            }
        }
        for(int r = 5; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                if(r % 2 == c % 2)
                {
                    boardVar[r][c] = new CheckerPiece("black", r, c);
                    allBlackPieces.add(boardVar[r][c]);
                }
            }
        }
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

    boolean canMove(CheckerPiece piece)
    {
        if(boardVar[piece.row][piece.col] == null)
        {
            return false;
        }
        else if(!piece.king)
        {
            if(piece.color.equals("red"))
            {
                return redCanJump(piece) || redCanMove(piece);
            }
            return blackCanJump(piece) || blackCanMove(piece);
        }
        if(piece.color.equals("red"))
        {
            return kingRedCanJump(piece) || redCanJump(piece)|| kingCanMove(piece);
        }
        return kingBlackCanJump(piece) || blackCanJump(piece) || kingCanMove(piece);
    }

    private boolean redCanMove(CheckerPiece piece)
    {
        if(piece.col == 0)
        {
            return boardVar[piece.row + 1][piece.col + 1] == null;
        }
        else if(piece.col == 7)
        {
            return boardVar[piece.row + 1][piece.col - 1] == null;
        }
        else
        {
            return boardVar[piece.row + 1][piece.col - 1] == null
                    || boardVar[piece.row + 1][piece.col + 1] == null;
        }
    }

    private boolean kingCanMove(CheckerPiece piece)
    {
        return (redCanMove(piece) && piece.row != 7) || (blackCanMove(piece) && piece.row != 0);
    }

    private boolean blackCanMove(CheckerPiece piece)
    {
        if(piece.col == 0)
        {
            return boardVar[piece.row - 1][piece.col + 1] == null;
        }
        else if(piece.col == 7)
        {
            return boardVar[piece.row - 1][piece.col - 1] == null;
        }
        else {
            return boardVar[piece.row - 1][piece.col - 1] == null
                    || boardVar[piece.row - 1][piece.col + 1] == null;
        }
    }

    private boolean redCanJump(CheckerPiece piece)
    {
        if (piece.row >= 6) {return false;}
        if(piece.col == 0 || piece.col == 1)
        {
            return boardVar[piece.row + 2][piece.col + 2] == null
                    && (boardVar[piece.row + 1][piece.col + 1] != null) &&
                    boardVar[piece.row + 1][piece.col + 1].color.equals("black");
        }
        else if(piece.col == 7 || piece.col == 6)
        {
            return boardVar[piece.row + 2][piece.col - 2] == null
                    && (boardVar[piece.row + 1][piece.col - 1] != null)
                    && boardVar[piece.row + 1][piece.col - 1].color.equals("black");
        }
        else
        {
            return (boardVar[piece.row + 2][piece.col + 2] == null &&
                    (boardVar[piece.row + 1][piece.col + 1] != null) &&
                    boardVar[piece.row + 1][piece.col + 1].color.equals("black")) ||
                    (boardVar[piece.row + 2][piece.col - 2] == null &&
                            (boardVar[piece.row + 1][piece.col - 1] != null)
                            && boardVar[piece.row + 1][piece.col - 1].color.equals("black"));
        }
    }

    private boolean kingRedCanJump(CheckerPiece piece)
    {
        if(piece.row <= 1)
        {
            return false;
        }
        if(piece.col == 0 || piece.col == 1)
        {
            return boardVar[piece.row - 2][piece.col + 2] == null &&
                    (boardVar[piece.row - 1][piece.col + 1] != null) &&
                    boardVar[piece.row - 1][piece.col + 1].color.equals("black");
        }
        else if(piece.col == 7 || piece.col == 6)
        {
            return boardVar[piece.row - 2][piece.col - 2] == null &&
                    (boardVar[piece.row - 1][piece.col - 1] != null &&
                            boardVar[piece.row - 1][piece.col - 1].color.equals("black"));
        }
        else
        {
            return (boardVar[piece.row + 2][piece.col + 2] == null &&
                    (boardVar[piece.row + 1][piece.col + 1] != null) &&
                    boardVar[piece.row + 1][piece.col + 1].color.equals("black")) ||
                    (boardVar[piece.row + 2][piece.col - 2] == null &&
                            (boardVar[piece.row + 1][piece.col - 1] != null)
                            && boardVar[piece.row + 1][piece.col - 1].color.equals("black"));
        }
    }

    private boolean blackCanJump(CheckerPiece piece)
    {
        if(piece.row <= 1)
        {
            return false;
        }
        if(piece.col == 0 || piece.col == 1)
        {
            return boardVar[piece.row - 2][piece.col + 2] == null &&
                    (boardVar[piece.row - 1][piece.col + 1] != null) &&
                    boardVar[piece.row - 1][piece.col + 1].color.equals("red");
        }
        else if(piece.col == 7 || piece.col == 6)
        {
            return boardVar[piece.row - 2][piece.col - 2] == null &&
                    (boardVar[piece.row - 1][piece.col - 1] != null &&
                            boardVar[piece.row - 1][piece.col - 1].color.equals("red"));
        }
        else
        {
            return (boardVar[piece.row + 2][piece.col + 2] == null &&
                    (boardVar[piece.row + 1][piece.col + 1] != null) &&
                    boardVar[piece.row + 1][piece.col + 1].color.equals("red")) ||
                    (boardVar[piece.row + 2][piece.col - 2] == null &&
                            (boardVar[piece.row + 1][piece.col - 1] != null)
                            && boardVar[piece.row + 1][piece.col - 1].color.equals("red"));
        }
    }

    private boolean kingBlackCanJump(CheckerPiece piece)
    {
        if(piece.row >= 6)
        {
            return false;
        }
        if(piece.col == 0 || piece.col == 1)
        {
            return boardVar[piece.row + 2][piece.col + 2] == null
                    && (boardVar[piece.row + 1][piece.col + 1] != null) &&
                    boardVar[piece.row + 1][piece.col + 1].color.equals("red");
        }
        else if(piece.col == 7 || piece.col == 6)
        {
            return boardVar[piece.row + 2][piece.col - 2] == null
                    && (boardVar[piece.row + 1][piece.col - 1] != null)
                    && boardVar[piece.row + 1][piece.col - 1].color.equals("red");
        }
        else
        {
            return (boardVar[piece.row + 2][piece.col + 2] == null &&
                    (boardVar[piece.row + 1][piece.col + 1] != null) &&
                    boardVar[piece.row + 1][piece.col + 1].color.equals("red")) ||
                    (boardVar[piece.row + 2][piece.col - 2] == null &&
                            (boardVar[piece.row + 1][piece.col - 1] != null)
                            && boardVar[piece.row + 1][piece.col - 1].color.equals("red"));
        }
    }

    public CheckersBoard()
    {
        boardVar = new CheckerPiece[8][8];  //0 = empty, 1 = red, 2 = black,
                                            // 3 = red king, 4 = black king
        initArray();
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}
