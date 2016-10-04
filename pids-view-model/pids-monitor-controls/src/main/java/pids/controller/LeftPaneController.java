package pids.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fx.controller.SimpleController;
import pids.config.MonitorConstants;
import pids.controls.BorderedPane;
import pids.view.ScrollMapPane;

@Component
public class LeftPaneController extends SimpleController<SplitPane> implements Initializable {
	protected final ScrollMapPaneController<? extends ScrollMapPane> map;
	protected final CameraViewController cameraViewController;
    @Autowired
	public LeftPaneController(ScrollMapPaneController<? extends ScrollMapPane> map, CameraViewController cameraViewController) {
    	super();
    	this.map = map;
    	this.cameraViewController = cameraViewController;
	}
    private static final String ADDRESS = "Kindly contat chief security officer. Extn : 6512 generate voice activated alarm at thiis zone";
	@Override
	public void load() {
		load(this, MonitorConstants.FXML_LEFT_PANE);
	}
	@FXML
    private BorderedPane cameraView;
    @FXML
    private BorderedPane mapView;
    @FXML
    private Label instructionlbl;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mapView.setContent(map.getContent());
		cameraView.setContent(cameraViewController.getContent());
		instructionlbl.setText(ADDRESS);
	}
}