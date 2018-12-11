package cs1302.arcade;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class ArcadeApp extends Application {

    @Override
    public void start(Stage stage)
	{

		VBox vBox = new VBox();           // main container
        Text pickGame = new Text("Select Game");
        pickGame.setFont(Font.font(40));

        Button spaceInvaders = new Button("Space Invaders");
        spaceInvaders.setFont(Font.font(40));
        //spaceInvaders.setOnAction( -> );
        Button americanCheckers = new Button("American Checkers");
        americanCheckers.setFont(Font.font(40));
        //americanCheckers.setOnAction( -> );

        vBox.getChildren().addAll(pickGame, spaceInvaders, americanCheckers);


		Scene scene = new Scene(vBox, 640, 480);
		stage.setTitle("cs1302-arcade!");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();

    }

    public static void main(String[] args)
	{
		try
		{
	    	Application.launch(args);
		}
		catch(UnsupportedOperationException e)
		{
			System.out.println(e);
			System.err.println("If this is a DISPLAY problem, then your X server connection");
			System.err.println("has likely timed out. This can generally be fixed by logging");
			System.err.println("out and logging back in.");
			System.exit(1);
		}
    }
}

