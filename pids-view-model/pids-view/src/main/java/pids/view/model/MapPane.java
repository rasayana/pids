package pids.view.model;

import fx.controls.ImagePane;
import pids.core.Data;
import pids.core.Perimeter;
import pids.view.core.PIDSEvent;
import pids.view.core.PContainer;
import pids.view.core.PPerimeter;
import pids.view.core.PerimeterCanvas;
import pids.view.core.PerimeterAttribute;
import pids.view.core.PIDSEventDispatcher;
import javafx.scene.shape.Shape;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.adapter.JavaBeanBooleanPropertyBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public final class MapPane extends ImagePane implements PerimeterCanvas {
	private final PContainer parentContainer;
    public MapPane(PContainer parentContainer) {
        super();
        this.parentContainer = parentContainer;
    }
    private Data data = null;
	@Override
	public final PContainer thisParent() {
		return parentContainer;
	}
	@Override
	public final Data model() {
		return data;
	}
	@Override
	public final MapPane pidsPane() {
		return this;
	}
	private ObservableMap<Perimeter, PPerimeter> perimeters = FXCollections.observableHashMap();
	@Override
	public final ObservableMap<Perimeter, PPerimeter> list() {
		return perimeters;
	}
	@Override
	public final PPerimeter get(Perimeter p) {
		return perimeters.get(p);
	}
	private PerimeterAttribute getAttribute(Perimeter p) {
		PerimeterAttribute result = new PerimeterAttrib(p);
		result.setLineStrokeWidth(Constants.LINE_STROKE_WIDTH);
		result.setNodeStrokeWidth(Constants.ANCHOR_STROKE_WIDTh);
		result.setRadius(Constants.RADIUS);
		return result;
	}
	@Override
	public final PPerimeter add(Perimeter perimeter) throws Exception {
    	PPerimeter result = new PerimeterObject(getAttribute(perimeter), this);
    	perimeters.put(perimeter, result);
    	return result;
	}
	@Override
	public final void addPNodes(ObservableList<Shape> shapes) {
		getChildren().addAll(shapes);
	}
	private BooleanProperty monitoringProperty;
	@Override
	public final BooleanProperty monitoringProperty() {
		return monitoringProperty;
	}
	@Override
    public final void setData(Data value) throws Exception {
    	if (value != null && !value.equals(data)) {
	        data = value;
	        monitoringProperty = JavaBeanBooleanPropertyBuilder.create().bean(model()).beanClass(Data.class).name("monitoring").build();
	        rebuildBackground();
	        rebuildPerimeters();
    	}
    }
	@Override
	public final void rebuildPerimeters() throws Exception {
        getChildren().clear();
        if (data != null) {
	        Perimeter head = data.head();
	        if (head != null) {
	            Perimeter p = head;
	            do {
	            	add(p);
	                p = p.next();
	            } while (!head.equals(p));
	        }
        }
	}
	@Override
	public final void rebuildBackground() {
        if (data != null)
        	//setBackground(data.mapProperties().stream());
        	setBGURL(data.mapProperties().image(), data.mapProperties().getWidth(), data.mapProperties().getHeight());
	}
	@Override
	public void dispatchPIDSEvent(PIDSEvent event) {
		((PIDSEventDispatcher) thisParent()).dispatchPIDSEvent(event);
	}
}