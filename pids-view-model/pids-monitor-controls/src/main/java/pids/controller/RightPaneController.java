package pids.controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import pids.config.MonitorConstants;
import fx.controller.SimpleController;
import pids.controls.BorderedPane;
import pids.view.ZoomMapPane;

@Component
public class RightPaneController extends SimpleController<SplitPane> implements Initializable {
	protected final ZoomMapPaneController<? extends ZoomMapPane> map;
	protected final EventLogController eventog;
	protected final AlarmInfoController alarmInfo;
	@Autowired
	public RightPaneController(ZoomViewController map, EventLogController eventLog, AlarmInfoController alarmInfo) {
		super();
		this.map = map;
		this.eventog = eventLog;
		this.alarmInfo = alarmInfo;
	}
	@Override
	public void load() {
		load(this, MonitorConstants.FXML_RIGHT_PANE);
	}
    @FXML
    private BorderedPane mapContainer;
    @FXML
    private Tab eventPane;
    @FXML
    private BorderedPane alarmInfoContaainer;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	mapContainer.setContent(map.getContent());
    	eventPane.setContent(eventog.getContent());
    	alarmInfoContaainer.setContent(alarmInfo.getContent());
	}
}