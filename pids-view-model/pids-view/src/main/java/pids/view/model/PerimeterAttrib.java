package pids.view.model;

import pids.core.Perimeter;
import pids.view.core.PerimeterAttribute;

final class PerimeterAttrib implements PerimeterAttribute {
	private final Perimeter perimeter;
	public PerimeterAttrib(Perimeter perimeter) {
		this.perimeter = perimeter;
	}
	@Override
	public final Perimeter getPerimeter() {
		return perimeter;
	}
	private double lineStrokeWidth;
	@Override
	public final double getLineStrokeWidth() {
		return lineStrokeWidth;
	}
	@Override
	public final void setLineStrokeWidth(double value) {
		lineStrokeWidth = value;
	}
	private double radius;
	@Override
	public final double getRadius() {
		return radius;
	}
	@Override
	public final void setRadius(double value) {
		radius = value;
	}
	private double nodeStrokeWidth;
	@Override
	public final double getNodeStrokeWidth() {
		return nodeStrokeWidth;
	}
	@Override
	public final void setNodeStrokeWidth(double value) {
		nodeStrokeWidth = value;
	}
}