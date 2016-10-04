package pids.view.model;

import pids.core.Node;
import pids.core.Device;
import pids.core.Toggle;
import pids.core.Hooter;
import pids.core.Camera;
import pids.core.Anchor;
import pids.view.core.PShape;
import pids.view.core.PSector;

final class Util {
    static ShapeProperty<?> getShapeProperty(Node<?> node, PSector sector) throws NoSuchMethodException {
        return (node instanceof Toggle)
                ? node instanceof Device
                        ? (node instanceof Camera) ? new PToggle.PCamera(null, (Camera) node, sector) : new PToggle.PHooter(null, (Hooter) node, sector)
                        : new PToggle.PAnchor(null, (Anchor) node, sector)
                : null;
    }
    private static <T extends PShape, Y extends ShapeProperty<T>> void setShape(Y prop, T shape) {
        if (shape != null && prop != null)
            prop.setShape(shape);
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	static <T extends PShape> T getShape(ShapeProperty<T> prop) {
        T result = null;
        if (prop != null) {
            if (prop instanceof PNode) {
            	Node<?> node = ((PNode) prop).getNode();
            	result = node instanceof Device ? (node instanceof Camera) ? (T) new NodeCamera((PToggle.PCamera) prop, prop.thisParent()) : (T) new NodeHooter((PToggle.PHooter) prop, prop.thisParent())
            				: (T) new NodeControl((PNode) prop, prop.thisParent());
            }
            setShape(prop, result);
        }
        return result;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	static <T extends PShape> T getShape(NodeProperty p1, NodeProperty p2, PSector sector) {
    	T result = null;
    	if (p1 != null && p2 != null) {
            BoundNode prop = new BoundNode<NodeProperty<Node<?>>>(null, p1, p2, sector);
            result = (T) new BoundLine(prop, prop.thisParent());
            setShape(prop, result);
        }
        return result;
    }
}