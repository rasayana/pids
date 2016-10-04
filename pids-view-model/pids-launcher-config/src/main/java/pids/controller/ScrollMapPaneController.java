package pids.controller;

import fx.controller.JavaController;
import pids.view.ScrollMapPane;

public class ScrollMapPaneController<T extends ScrollMapPane> extends JavaController<T> {
	public ScrollMapPaneController(T pane) {
		super(pane);
	}
}
