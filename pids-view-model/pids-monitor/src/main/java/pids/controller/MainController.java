package pids.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pids.config.AppConstants;
import fx.controller.SimpleController;
import pids.launcher.DataService;

@Component
public class MainController extends SimpleController<SplitPane> implements Initializable {
	protected final RightPaneController rightPane;
	protected final LeftPaneController leftPane;
	@Autowired
	public MainController(RightPaneController rightPane, LeftPaneController leftPane) {
		super();
		this.rightPane = rightPane;
		this.leftPane = leftPane;
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
    private SplitPane mainWindow;
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	mainWindow.getItems().addAll(leftPane.getContent(), rightPane.getContent());
	}
}