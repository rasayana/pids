package pids.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDateTime;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fx.controller.SimpleController;
import pids.view.core.IntrusionEvent;
//import pids.view.core.Zone;
import pids.config.EventLogConstants;
import pids.view.ScrollMapPane;
import pids.view.ZoomMapPane;
import pids.view.model.FXIntrusionEvent;

@Component
public class EventLogController extends SimpleController<TableView<FXIntrusionEvent>> implements Initializable, EventHandler<FXIntrusionEvent> {
	private final ScrollMapPaneController<? extends ScrollMapPane> lmap;
	private final ZoomMapPaneController<? extends ZoomMapPane> rmap;
	private final AlarmInfoController alarmInfoController;
    @Autowired
	public EventLogController(ZoomMapPaneController<? extends ZoomMapPane> rmap, ScrollMapPaneController<? extends ScrollMapPane> lmap, AlarmInfoController alarmInfoController) {
    	super();
    	this.rmap = rmap;
    	this.lmap = lmap;
    	this.alarmInfoController = alarmInfoController;
    	this.lmap.getContent().addEventHandler(FXIntrusionEvent.INTRUSION_EVENT, this);
    	this.rmap.getContent().addEventHandler(FXIntrusionEvent.INTRUSION_EVENT, this);
	}
	private ObservableList<FXIntrusionEvent> eventData = FXCollections.observableArrayList();
	@Override
	public void load() {
		load(this, EventLogConstants.FXML_EVENT_LOG);
	}
	@FXML
    private TableView<FXIntrusionEvent> eventView;
	@FXML
	private TableColumn<FXIntrusionEvent, LocalDateTime> timeClm;
	@FXML
	private TableColumn<FXIntrusionEvent, IntrusionEvent.Severity> severityClm;
	@FXML
	private TableColumn<FXIntrusionEvent, Boolean> handledClm;
	@FXML
	private TableColumn<FXIntrusionEvent, String> zoneClm;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		eventView.setItems(eventData);
		timeClm.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
		severityClm.setCellValueFactory(cellData -> cellData.getValue().severityProperty());
		handledClm.setCellValueFactory(cellData -> cellData.getValue().handledProperty());
		zoneClm.setCellValueFactory(cellData -> cellData.getValue().zoneProperty());
		eventView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
			    if(eventView.getSelectionModel().getSelectedItem() != null)
			    	alarmInfoController.setInfo(eventView.getSelectionModel().getSelectedItem());
			    else alarmInfoController.setInfo(null);
			}
		});
	}
	@Override
    public void handle(FXIntrusionEvent event) {
    	eventData.add(event);
    }
}