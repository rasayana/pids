package pids.view;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import fx.controls.ZoomingPane;
import pids.core.Data;
import pids.core.Model;
import pids.view.core.PIDSEvent;
import pids.view.core.PContainer;
import pids.view.core.PModel;
import pids.view.core.PShape;
import pids.view.core.PerimeterCanvas;
import pids.view.core.SetData;

public class ZoomMapPane extends BorderPane implements PContainer, SetData {
	private final ScrollMapPane mapPane = new ScrollMapPane(this);
	private final ZoomingPane zoomPane = new ZoomingPane(mapPane);
    private final Slider slider = new Slider(0.1, 5, 1);
	@Override
	public PerimeterCanvas perimeterCanvas() {
		return mapPane.perimeterCanvas();
	}
	public ZoomMapPane() {
		super();
		zoomPane.zoomFactorProperty().bind(slider.valueProperty());
	}
	@Override
	public Data model() {
		return mapPane.model();
	}
	@Override
	public PModel thisParent() {
		return null;
	}
	@Override
	public void setData(Data value) throws Exception {
    	if (value != null && !value.equals(model()))
    		mapPane.setData(value);
    	setCenterShape(true);
    	setTop(slider);
    	setCenter(zoomPane);
	}
	@Override
	public void dispatchPIDSEvent(PIDSEvent event) {
		fireEvent((Event) event);
	}
	@Override
	public Node getChildFrom(Model<?, ?> model) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Model<?, ?> getChildFrom(Node node) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void showProperties(PShape child) {
		// TODO Auto-generated method stub

	}
}