package pids.view.core;

import pids.core.Perimeter;

public interface PerimeterAttribute {
	Perimeter getPerimeter();
	double getLineStrokeWidth();
	void setLineStrokeWidth(double value);
	double getRadius();
	void setRadius(double value);
	double getNodeStrokeWidth();
	void setNodeStrokeWidth(double value);
}
