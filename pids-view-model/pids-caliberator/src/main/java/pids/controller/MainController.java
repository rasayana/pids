package pids.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fx.controller.SimpleController;
import pids.config.AppConstants;
import pids.launcher.DataService;
import pids.view.ZoomMapPane;

@Component
public class MainController extends SimpleController<VBox> implements Initializable {
	protected final ZoomMapPaneController<? extends ZoomMapPane> mapPaneController;
    @Autowired
	public MainController(ZoomMapPaneController<? extends ZoomMapPane> mapPaneController) {
		super();
		this.mapPaneController = mapPaneController;
	}
	@Override
	public void load() {
		load(this, AppConstants.FXML_DESIGN_MAIN);
	}
    @Autowired
	private DataService pidsService;
	@FXML
    public void saveData() throws Exception {
    	pidsService.save();
	}
    @FXML
    private VBox mapBox;
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	mapBox.getChildren().add(mapPaneController.getContent());
	}
}