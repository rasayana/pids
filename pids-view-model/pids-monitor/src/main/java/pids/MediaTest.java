package pids;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MediaTest extends Application {

    private static final String MEDIA_URL = "http://webcast-west.sun.com/oow2010.flv";
	public static void main1(String[] args) {
        launch(args);
    }
    @Override
	public void start(Stage stage) {
	     // Create and set the Scene.
	     Scene scene = new Scene(new Group(), 540, 209);
	     stage.setScene(scene);

	     // Name and display the Stage.
	     stage.setTitle("Hello Media");
	     stage.show();
	     Media media = new Media(MEDIA_URL);
	     MediaPlayer mediaPlayer = new MediaPlayer(media);
	     mediaPlayer.onReadyProperty().set(() -> mediaPlayer.play());

	     // Create the view and add it to the Scene.
	     MediaView mediaView = new MediaView(mediaPlayer);
	     ((Group) scene.getRoot()).getChildren().add(mediaView);
	     System.out.println(media.errorProperty().asString());

	 }
}
