package pids.view.edit;

import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import pids.core.Anchor;

public class AnchorPropertyDialog extends Dialog<Anchor> {
	public AnchorPropertyDialog() {
        setResultConverter((dialogButton) -> {
            ButtonData data = dialogButton == null ? null : dialogButton.getButtonData();
            return data == ButtonData.OK_DONE ? null /* new Anchor() */ : null;
        });
	}
}
