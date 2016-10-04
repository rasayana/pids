package pids.manager;

import org.springframework.beans.factory.annotation.Autowired;
import fx.manager.AbstractScreenManager;
import pids.controller.ScrollMapPaneController;
import pids.controller.ZoomMapPaneController;
import pids.view.ScrollMapPane;
import pids.view.ZoomMapPane;

public abstract class ScreenManager extends AbstractScreenManager {
    @Autowired
    protected ScrollMapPaneController<? extends ScrollMapPane> scrollMapPaneController;
    @Autowired
    protected ZoomMapPaneController<? extends ZoomMapPane> zoomMapPaneController;
}
