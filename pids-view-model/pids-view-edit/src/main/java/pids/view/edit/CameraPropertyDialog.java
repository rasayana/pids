package pids.view.edit;

import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import pids.core.Camera;

public class CameraPropertyDialog extends Dialog<Camera> {
	public CameraPropertyDialog() {
        setResultConverter((dialogButton) -> {
            ButtonData data = dialogButton == null ? null : dialogButton.getButtonData();
            return data == ButtonData.OK_DONE ? null /* new Camera() */ : null;
        });
	}
}