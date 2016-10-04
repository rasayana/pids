package pids.view;

import javafx.application.Platform;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import pids.core.Data;
import pids.core.Model;
import pids.view.core.PIDSEvent;
import pids.view.core.PContainer;
import pids.view.core.PModel;
import pids.view.core.PShape;
import pids.view.core.PerimeterCanvas;
import pids.view.core.SetData;
import pids.view.model.MapPane;

public class ViewMapPane extends BorderPane implements PContainer, SetData {
	private final PerimeterCanvas canvas = new MapPane(this);
	private FloatProperty mapRatioProperty;
	@Override
	public Data model() {
		return (Data) canvas.model();
	}
	public ViewMapPane() {
		super();
		mapRatioProperty = new SimpleFloatProperty(0.4f);
	}
	@Override
	public PModel thisParent() {
		return null;
	}
	@Override
	public void dispatchPIDSEvent(PIDSEvent event) {
		fireEvent((Event) event);
	}
	@Override
	public void setData(Data value) throws Exception {
    	if (value != null && !value.equals(model()))
    		canvas.setData(value);
    	setCenterShape(true);
    	setCenter((Node) canvas);
        widthProperty().addListener((observable, oldValue, newValue) -> { fitImageViewSize(newValue.floatValue(), (float) getHeight()); });
        heightProperty().addListener((observable, oldValue, newValue) -> { fitImageViewSize((float) getWidth(), newValue.floatValue()); });
        mapRatioProperty.addListener((observable, oldValue, newValue) -> { fitImageViewSize((float) getWidth(), (float) getHeight()); });
	}
    private void fitImageViewSize(float width, float height) {
        Platform.runLater(() -> {
            float fitHeight = mapRatioProperty.get() * width;
        	Node map = (Node) canvas;
            if (fitHeight > height) {
            	map.prefHeight(height);
                double fitWidth = height / mapRatioProperty.get();
                map.prefWidth(fitWidth);
//                map.setX((width - fitWidth) / 2);
//                imageView.setY(0);
            } else {
                map.prefWidth(width);
                map.prefHeight(fitHeight);
//                imageView.setY((height - fitHeight) / 2);
//                imageView.setX(0);
            }
        });
    }
	@Override
	public PerimeterCanvas perimeterCanvas() {
		return canvas;
	}
	@Override
	public Node getChildFrom(Model<?, ?> model) {
		return null;
	}
	@Override
	public Model<?, ?> getChildFrom(Node node) {
		return null;
	}
	@Override
	public void showProperties(PShape child) {
		// TODO Auto-generated method stub

	}
}