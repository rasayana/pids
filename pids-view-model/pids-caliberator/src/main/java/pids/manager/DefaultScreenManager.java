package pids.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fx.controller.Controller;
import pids.controller.MainController;

@Component
public class DefaultScreenManager extends ScreenManager {
    @Autowired
	private MainController mainController;
	@Override
	public Controller<?> getMainController() {
		return mainController;
	}
}
