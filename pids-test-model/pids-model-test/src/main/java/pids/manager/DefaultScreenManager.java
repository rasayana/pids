package pids.manager;

import org.springframework.stereotype.Component;
import fx.controller.Controller;

@Component
public class DefaultScreenManager extends ScreenManager {
	@Override
	public Controller<?> getMainController() {
		return zoomMapPaneController;
	}

}
