package pids.view.core;

import javafx.scene.Node;
import pids.core.Model;

public interface PContainer extends PModel, PIDSEventDispatcher {
	PerimeterCanvas perimeterCanvas();
	Node getChildFrom(Model<?, ?> model);
	Model<?, ?> getChildFrom(Node node);
	void showProperties(PShape child);
}