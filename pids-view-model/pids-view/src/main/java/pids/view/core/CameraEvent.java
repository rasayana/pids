package pids.view.core;

import pids.core.Camera;

public interface CameraEvent extends PIDSEvent {
	Camera getCamera();
}