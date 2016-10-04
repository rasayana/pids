package pids.view.core;

import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;

public interface PSector extends PContainer, DisplayList {
	SectorAttribute getAttribute();
	void addShape(PShape s);
	ObjectProperty<Color> color();
	ObjectProperty<Color> strokeColor();
	ObjectProperty<Color> lineColor();
	ObjectProperty<Color> crossOverColor();
	ObjectProperty<Color> intrusionColor();
	ObjectProperty<Color> cameraColor();
	ObjectProperty<Color> hooterColor();
}