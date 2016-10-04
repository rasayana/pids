package pids.view.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.adapter.JavaBeanDoublePropertyBuilder;
import pids.core.Node;
import pids.view.core.PSector;

class NodeProperty<N extends Node<?>> extends ShapeProperty<NodeControl<?, ?>> {
    private final N node;
    N getNode() {
        return node;
    }
    private final DoubleProperty xProperty;
    private final DoubleProperty yProperty;
    private JavaBeanDoublePropertyBuilder nodeXYPropertybuilder() {
    	return JavaBeanDoublePropertyBuilder.create().bean(getNode()).beanClass(Node.class);
    }
    NodeProperty(NodeControl<?, ?> shape, N node, PSector sector) throws NoSuchMethodException {
        super(shape, sector);
        this.node = node;
        xProperty = nodeXYPropertybuilder().name("X").build();
        yProperty = nodeXYPropertybuilder().name("Y").build();
    }
    DoubleProperty xProperty() {
        return xProperty;
    }
    DoubleProperty yProperty() {
        return yProperty;
    }
}