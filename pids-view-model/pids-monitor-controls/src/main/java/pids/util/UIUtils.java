package pids.util;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.control.ScrollPane;
import fx.vlcj.MediaPane;
import pids.controls.BorderedPane;

public abstract class UIUtils {
	public static BorderedPane wrap(Node node) {
		BorderedPane pane = new BorderedPane();
		pane.setContent(node);
		if (node instanceof Region)
			pane.setPrefSize(((Region) node).getPrefWidth(), ((Region) node).getPrefHeight());
		return pane;
	}
	public static Node getCameraViewFX(String source) {
		MediaPlayer mediaPlayer = new MediaPlayer(new Media(source));
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		MediaView view = new MediaView(mediaPlayer);
		view.setPreserveRatio(true);
		view.setFitHeight(250);
		view.setFitWidth(250);
		return view;
	}
	public static Node getCameraView(String source) {
		MediaPane mplayer = new MediaPane();
		mplayer.play(source);
		return mplayer;
	}
	public void centerNodeInScrollPane(ScrollPane scrollPane, Node node) {
	    double h = scrollPane.getContent().getBoundsInLocal().getHeight();
	    double y = (node.getBoundsInParent().getMaxY() + node.getBoundsInParent().getMinY()) / 2.0;
	    double v = scrollPane.getViewportBounds().getHeight();
	    scrollPane.setVvalue(scrollPane.getVmax() * ((y - 0.5 * v) / (h - v)));
	}
}