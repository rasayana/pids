package pids.controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.stereotype.Component;
import fx.controller.SimpleController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import pids.config.EventLogConstants;
import pids.view.model.FXIntrusionEvent;

@Component
public class AlarmInfoController extends SimpleController<HBox> implements Initializable {
	public AlarmInfoController() {
	}
	@Override
	public void load() {
		load(this, EventLogConstants.FXML_ALARM_INFO);
	}
	@FXML
    private Label lblTime;
	@FXML
	private Label lblSeverity;
	@FXML
	private Label lblZone;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	public void setInfo(FXIntrusionEvent info) {
		lblTime.setText(info == null ? "" : info.getTime().toString());
		lblSeverity.setText(info == null ? "" : info.getSeverity().toString());
		lblZone.setText(info == null ? "" : info.zoneProperty().get());
	}
}