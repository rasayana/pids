package pids.view.model;

import pids.core.Camera;
import pids.view.core.PSector;
import pids.view.model.PToggle.PCamera;

public class NodeCamera extends NodeControl<PCamera, Camera> {
	public NodeCamera(PCamera node, PSector sector) {
		super(node, sector);
	}
}
