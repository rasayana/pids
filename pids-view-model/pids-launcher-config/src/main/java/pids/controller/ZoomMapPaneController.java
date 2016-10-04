package pids.controller;

import fx.controller.JavaController;
import pids.view.ZoomMapPane;

public class ZoomMapPaneController<T extends ZoomMapPane> extends JavaController<T> {
	public ZoomMapPaneController(T pane) {
		super(pane);
	}
}
