package pids.controls;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.DefaultResourceLoader;
import fx.controls.TitledBorder;
import pids.config.MonitorConstants;

public class BorderedPane extends TitledBorder {
	private final ResourceLoader loader = new DefaultResourceLoader();
	protected final Resource getResource(String url) {
		return loader.getResource(url);
	}
    public BorderedPane() {
		FXMLLoader fxmlLoader;
		try { fxmlLoader = new FXMLLoader(getResource(MonitorConstants.FXML_TITLEDBORDER).getURL());
	        fxmlLoader.setRoot(this); fxmlLoader.setController(this); fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}