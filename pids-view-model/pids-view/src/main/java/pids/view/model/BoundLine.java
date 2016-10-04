package pids.view.model;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;
import pids.core.Node;
import pids.core.Anchor;
import pids.core.Camera;
import pids.view.core.IntrusionEvent;
import pids.view.core.PSector;
import pids.view.core.Zone;

class BoundLine<T extends BoundNode<?>> extends Line implements Zone {
    private final T node;
    final T getNode() {
        return (T) node;
    }
	@Override
	public final PSector thisParent() {
		return sector;
	}
    @Override
    public final Node<?> model() {
        return getNode().getStartNode();
    }
	@Override
	public final Node<?> other() {
        return getNode().getEndNode();
    }
    @Override
    public final BoundLine<T> shape() {
        return this;
    }
    @SuppressWarnings("unused")
	private EventHandler<MouseEvent> getHandlerfor(final ObjectProperty<Color> prop, IntrusionEvent.Severity severity, boolean playAnimation) {
        return (MouseEvent mouseEvent) -> {
            if (model().isMonitoring()) {
                strokeProperty().bind(prop);
                if (playAnimation)
                	animation.playFromStart();
                else animation.stop();
                if(severity != null) {
                	thisParent().dispatchPIDSEvent(new FXIntrusionEvent(this, severity));
                	Camera camera = null;
                	Anchor anchor = (Anchor) model();
                	/*
                	if (anchor.devices().length > 0)
                		camera = anchor.devices()[0];
                	else {
                		anchor = (Anchor) other();
                		if (anchor.cameras().length > 0)
                			camera = anchor.cameras()[0];
                	}
					*/
                	if (camera != null) {

                	}
                	if (camera != null)
                    	thisParent().dispatchPIDSEvent(FXCameraEvent.getForEventType(camera, FXCameraEvent.CAMERA_URL_TYPE));
                }
            }
        };
    }
    private final FadeTransition animation;
    private final PSector sector;
    BoundLine(T node, PSector sector) {
        super();
        this.sector = sector;
        this.node = node;
        animation = new FadeTransition(Duration.millis(1000), this);
        animation.setFromValue(1.0);
        animation.setToValue(0);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.setAutoReverse(true);
        // mouseTransparentProperty().bind(sector.perimeterCanvas().monitoringProperty());
        startXProperty().bind(this.node.getStartX());
        startYProperty().bind(this.node.getStartY());
        endXProperty().bind(this.node.getEndX());
        endYProperty().bind(this.node.getEndY());
        strokeWidthProperty().bind(this.node.strokeWidth());
        strokeProperty().bind(this.node.color());
        setStrokeLineCap(StrokeLineCap.BUTT);
        setOnMousePressed(getHandlerfor(this.node.intrusionColor(), IntrusionEvent.Severity.P1, true));
        setOnMouseReleased(getHandlerfor(this.node.color(), null, false));
        setOnMouseEntered(getHandlerfor(this.node.crossOverColor(), IntrusionEvent.Severity.P4, true));
        setOnMouseExited(getHandlerfor(this.node.color(), null, false));
    }
}