package pids.manager;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fx.controller.Controller;
import pids.controller.MainController;
import pids.launcher.DataService;

@Component
public class DefaultScreenManager extends ScreenManager {
    @Autowired
	private MainController mainController;
    @Autowired
	private DataService client;
	@PostConstruct
	private void prepareMapData() throws Exception {
		client.getData().setMonitoring(true);
	}
	@Override
	public Controller<?> getMainController() {
		return mainController;
	}
}
