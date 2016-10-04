package pids.view.core;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import pids.core.Perimeter;
import javafx.beans.property.BooleanProperty;

public interface PerimeterCanvas extends PModel, SetData, Container<Perimeter, PPerimeter>, PIDSEventDispatcher {
	Pane pidsPane();
	void addPNodes(ObservableList<Shape> shapes);
	BooleanProperty monitoringProperty();
	void rebuildPerimeters() throws Exception;
	void rebuildBackground();
}