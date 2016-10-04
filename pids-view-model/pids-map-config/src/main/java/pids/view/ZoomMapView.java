package pids.view;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pids.launcher.DataService;

@Component
public final class ZoomMapView extends ZoomMapPane {
    @Autowired
	private DataService client;
	public ZoomMapView() throws Exception {
		super();
	}
	@PostConstruct
	void prepareService() throws Exception {
		setData(client.getData());
	}
}