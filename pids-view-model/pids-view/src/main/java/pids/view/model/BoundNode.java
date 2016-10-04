package pids.view.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import pids.core.Node;
import pids.view.core.PSector;
import pids.view.core.PPerimeter;

class BoundNode<T extends NodeProperty<?>> extends ShapeProperty<BoundLine<?>> {
    private final T start;
    final DoubleProperty getStartX() {
        return start.xProperty();
    }
    final DoubleProperty getStartY() {
        return start.yProperty();
    }
    final Node<?> getStartNode() {
        return start.getNode();
    }
    private final T end;
    final DoubleProperty getEndX() {
        return end.xProperty();
    }
    final DoubleProperty getEndY() {
        return end.yProperty();
    }
    final Node<?> getEndNode() {
        return end.getNode();
    }
    final DoubleProperty strokeWidth() {
        return ((PPerimeter) thisParent().thisParent()).strokeWidth();
    }
    final ObjectProperty<Color> color() {
        return thisParent().lineColor();
    }
    final ObjectProperty<Color> crossOverColor() {
        return thisParent().crossOverColor();
    }
    final ObjectProperty<Color> intrusionColor() {
        return thisParent().intrusionColor();
    }
    BoundNode(BoundLine<?> shape, T start, T end, PSector sector) {
        super(shape, sector);
        this.start = start;
        this.end = end;
    }
}