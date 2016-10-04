package pids.view.model;

import javafx.scene.paint.Color;
import pids.core.Sector;
import pids.view.core.SectorAttribute;

final class SectorAttrib implements SectorAttribute {
	private final Sector sector;
	SectorAttrib(Sector sector) {
		this.sector = sector;
	}
	@Override
	public final Sector getSector() {
		return sector;
	}
	private Color nodeColor;
	@Override
	public final Color getNodeColor() {
		return nodeColor;
	}
	@Override
	public final void setNodeColor(Color value) {
		nodeColor = value;
	}
	private Color nodeStrokeColor;
	@Override
	public final Color getNodeStrokeColor() {
		return nodeStrokeColor;
	}
	@Override
	public final void setNodeStrokeColor(Color value) {
		nodeStrokeColor = value;
	}
	private Color lineColor;
	@Override
	public final Color getLineColor() {
		return lineColor;
	}
	@Override
	public final void setLineColor(Color value) {
		lineColor = value;
	}
	private Color crossOverColor;
	@Override
	public final Color getCrossOverColor() {
		return crossOverColor;
	}
	@Override
	public final void setCrossOverColor(Color value) {
		crossOverColor = value;
	}
	private Color intrusionColor;
	@Override
	public final Color getIntrusionColor() {
		return intrusionColor;
	}
	@Override
	public final void setIntrusionColor(Color value) {
		intrusionColor = value;
	}
	private Color cameraColor;
	@Override
	public final Color getCameraColor() {
		return cameraColor;
	}
	@Override
	public final void setCameraColor(Color value) {
		cameraColor = value;
	}
	private Color hooterColor;
	@Override
	public final Color getHooterColor() {
		return hooterColor;
	}
	@Override
	public final void setHooterColor(Color value) {
		hooterColor = value;
	}
}