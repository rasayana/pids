package pids.view.model;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import pids.core.Camera;
import pids.view.core.CameraEvent;

public final class FXCameraEvent extends Event implements CameraEvent {
	private static final long serialVersionUID = 8407204116266223739L;
	public static final String CAMERA_URL_TYPE = "CAMERA_URL";
	public static final String MOVE_EVENT_TYPE = "MOVE_EVENT";
	public static final EventType<FXCameraEvent> MOVE_EVENT = new EventType<FXCameraEvent>(ANY, MOVE_EVENT_TYPE);
	public static final FXCameraEvent getForEventType(Camera c, String eventType) {
		EventType<FXCameraEvent> eType = new EventType<FXCameraEvent>(ANY, eventType);
		return new FXCameraEvent(eType, c);
	}
	private final Camera camera;
    public FXCameraEvent(EventType<? extends Event> arg0, Camera camera) {
        this(null, null, arg0, camera);
    }
    public FXCameraEvent(Object arg0, EventTarget arg1, EventType<? extends Event> arg2, Camera camera) {
        super(arg0, arg1, arg2);
        this.camera = camera;
    }
    @Override
    public final Camera getCamera() {
        return camera;
    }
}