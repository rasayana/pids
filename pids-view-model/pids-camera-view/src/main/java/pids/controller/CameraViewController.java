package pids.controller;

import javafx.event.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fx.controller.JavaController;
import fx.vlcj.MediaPane;
import pids.view.model.FXCameraEvent;
import pids.service.CameraService;

@Component
public class CameraViewController extends JavaController<MediaPane> implements EventHandler<FXCameraEvent> {
	public CameraViewController() {
		super(new MediaPane());
	}
	@Autowired
	private CameraService cameraService;
	@Override
	public void handle(FXCameraEvent event) {
		try {
			getContent().play(cameraService.getURL(event.getCamera()));
			event.consume();
//			event.getEventType().getName()
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}