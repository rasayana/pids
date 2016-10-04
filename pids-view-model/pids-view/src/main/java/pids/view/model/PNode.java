package pids.view.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import pids.view.core.PSector;
import pids.view.core.PPerimeter;
import pids.core.Node;

abstract class PNode<T extends Node<?>> extends NodeProperty<T> {
    PNode(NodeControl<?, ?> shape, T node, PSector sector) throws NoSuchMethodException {
        super(shape, node, sector);
    }
    final DoubleProperty rProperty() {
        return ((PPerimeter) thisParent().thisParent()).radiusProperty();
    }
    ObjectProperty<Color> color() {
        return thisParent().color();
    }
    final ObjectProperty<Color> strokeColor() {
        return thisParent().strokeColor();
    }
    final DoubleProperty strokeWidth() {
        return ((PPerimeter) thisParent().thisParent()).nodeStrokeWidth();
    }
}