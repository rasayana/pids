package pids.view;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pids.launcher.DataService;

@Component
public final class ScrollMapView extends ScrollMapPane {
    @Autowired
	private DataService client;
	public ScrollMapView() throws Exception {
		super();
	}
	@PostConstruct
	void prepareService() throws Exception {
		setData(client.getData());
	}
}
