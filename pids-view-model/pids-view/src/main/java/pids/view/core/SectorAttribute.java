package pids.view.core;

import pids.core.Sector;
import javafx.scene.paint.Color;

public interface SectorAttribute {
	Sector getSector();
	Color getNodeColor();
	void setNodeColor(Color value);
	Color getNodeStrokeColor();
	void setNodeStrokeColor(Color value);
	Color getLineColor();
	void setLineColor(Color value);
	Color getCrossOverColor();
	void setCrossOverColor(Color value);
	Color getIntrusionColor();
	void setIntrusionColor(Color value);
	Color getCameraColor();
	void setCameraColor(Color value);
	Color getHooterColor();
	void setHooterColor(Color value);
}