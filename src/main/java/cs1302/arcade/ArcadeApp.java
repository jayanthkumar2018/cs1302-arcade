package cs1302.arcade;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ArcadeApp extends Application {

    Random rng = new Random();

    @Override
    public void start(Stage stage)
	{

		Group group = new Group();           // main container
		Rectangle r = new Rectangle(20, 20); // some rectangle

		HBox gameList = new HBox();
        Text pickGame = new Text("Select Game");


		Scene scene = new Scene(group, 640, 480);
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

