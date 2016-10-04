package pids.view.core;

import javafx.beans.property.DoubleProperty;
import pids.core.Sector;

public interface PPerimeter extends PContainer, Container<Sector, PSector>, DisplayList {
	PerimeterAttribute getAttribute();
	DoubleProperty strokeWidth();
	DoubleProperty radiusProperty();
	DoubleProperty nodeStrokeWidth();
}
