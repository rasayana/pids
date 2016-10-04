package pids.view.model;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import pids.core.Node;
import pids.core.MapProperties;
import pids.view.core.PSector;
import pids.view.core.PShape;

class NodeControl<T extends PNode<A>, A extends Node<?>> extends Circle implements PShape {
    private final T node;
    private final PSector sector;
    NodeControl(T node, PSector sector) {
        super();
        this.node = node;
        this.sector = sector;
        preppare();
    }
    final T getNode() {
        return node;
    }
    @Override
    public final A model() {
        return (A) getNode().getNode();
    }
    @Override
    public final NodeControl<?, ?> shape() {
        return this;
    }
	@Override
	public final PSector thisParent() {
		return sector;
	}
	protected void prepareFill() {
        fillProperty().bind(getNode().color());
	}
    protected void preppare() {
        setStrokeType(StrokeType.OUTSIDE);
        prepareFill();
        strokeProperty().bind(getNode().strokeColor());
        strokeWidthProperty().bindBidirectional(getNode().strokeWidth());
        centerXProperty().bindBidirectional(getNode().xProperty());
        centerYProperty().bindBidirectional(getNode().yProperty());
        radiusProperty().bind(getNode().rProperty());
        final Delta dragDelta = new Delta();
        setOnMousePressed((MouseEvent mouseEvent) -> {
            if(!model().isMonitoring()) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = getCenterX() - mouseEvent.getX();
                dragDelta.y = getCenterY() - mouseEvent.getY();
                getScene().setCursor(Cursor.MOVE);
                mouseEvent.consume();
            }
        });
        setOnMouseDragged((MouseEvent mouseEvent) -> {
            if(!model().isMonitoring()) {
            	MapProperties prop = model().data().mapProperties();
                double newX = mouseEvent.getX() + dragDelta.x;
                if (newX > 0 && newX < prop.getWidth())
                	setCenterX(newX);
                double newY = mouseEvent.getY() + dragDelta.y;
                if (newY > 0 && newY < prop.getHeight())
                	setCenterY(newY);
                mouseEvent.consume();
            }
        });
        setOnMouseReleased((MouseEvent mouseEvent) -> {
            if(!model().isMonitoring()) {
                getScene().setCursor(Cursor.HAND);
                mouseEvent.consume();
            }
        });
        setOnMouseEntered(getHandlerforButtonPressed(Cursor.HAND));
        setOnMouseExited(getHandlerforButtonPressed(Cursor.DEFAULT));

        setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
//                if(t.getButton().toString().equals("SECONDARY"))
//               cm.show(circle,t.getScreenX(),t.getSceneY());
            }
        });

    }
    private EventHandler<MouseEvent> getHandlerforButtonPressed(final Cursor cursor) {
        return (MouseEvent mouseEvent) -> {
            if (!mouseEvent.isPrimaryButtonDown() && !model().isMonitoring()) {
                getScene().setCursor(cursor);
                mouseEvent.consume();
            }
        };
    }
    // records relative x and y co-ordinates.
    private class Delta { double x, y; }
}