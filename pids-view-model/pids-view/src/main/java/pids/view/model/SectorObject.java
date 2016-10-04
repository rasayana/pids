package pids.view.model;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import pids.core.Model;
import pids.core.Sector;
import pids.view.core.PIDSEvent;
import pids.view.core.PSector;
import pids.view.core.PPerimeter;
import pids.view.core.PerimeterCanvas;
import pids.view.core.SectorAttribute;
import pids.view.core.PShape;
import pids.view.core.PIDSEventDispatcher;

final class SectorObject implements PSector {
	private final PPerimeter perimeter;
	@Override
	public final PPerimeter thisParent() {
		return perimeter;
	}
    private final ObjectProperty<Color> nodeColor;
    private final ObjectProperty<Color> nodeStrokeColor;
    private final ObjectProperty<Color> intrusionColor;
    private final ObjectProperty<Color> crossOverColor;
    private final ObjectProperty<Color> lineColor;
    private final ObjectProperty<Color> cameraColor;
    private final ObjectProperty<Color> hooterColor;
	@Override
    public final ObjectProperty<Color> color() {
        return nodeColor;
    }
	@Override
    public final ObjectProperty<Color> strokeColor() {
        return nodeStrokeColor;
    }
	@Override
	public final ObjectProperty<Color> lineColor() {
        return lineColor;
    }
	@Override
    public ObjectProperty<Color> crossOverColor() {
        return crossOverColor;
    }
	@Override
    public final ObjectProperty<Color> intrusionColor() {
        return intrusionColor;
    }
	@Override
	public ObjectProperty<Color> cameraColor() {
		return cameraColor;
	}
	@Override
	public ObjectProperty<Color> hooterColor() {
		return hooterColor;
	}
	private SectorAttribute attribute;
	@Override
	public SectorAttribute getAttribute() {
		return attribute;
	}
	@SuppressWarnings("unchecked")
	private JavaBeanObjectPropertyBuilder<Color> builder() {
		return JavaBeanObjectPropertyBuilder.create().bean(attribute).beanClass(SectorAttribute.class);
	}
	@SuppressWarnings("unchecked")
	SectorObject(PPerimeter perimeter, SectorAttribute attribute) throws Exception {
		this.perimeter = perimeter;
		this.attribute = attribute;
		nodeColor = builder().name("NodeColor").build();
        nodeStrokeColor = builder().name("NodeStrokeColor").build();
        lineColor = builder().name("LineColor").build();
        crossOverColor = builder().name("CrossOverColor").build();
        intrusionColor = builder().name("IntrusionColor").build();
        cameraColor = builder().name("CameraColor").build();
        hooterColor = builder().name("HooterColor").build();
	}
	private final ObservableList<Shape> shapes = FXCollections.observableArrayList();
	@Override
	public final ObservableList<Shape> getList() {
		return shapes;
	}
	@Override
	public final PerimeterCanvas perimeterCanvas() {
		return perimeter.perimeterCanvas();
	}
	@Override
	public final Sector model() {
		return attribute.getSector();
	}
	@SuppressWarnings("rawtypes")
	@Override
    public final void addShape(PShape s) {
        if (s != null) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setOffsetX(2.0f);
            dropShadow.setOffsetY(2.0f);
            dropShadow.setColor(Color.rgb(50, 50, 50, .588));
            s.shape().setEffect(dropShadow);
            if (s instanceof NodeControl)
            	((NodeControl) s).toFront();
            shapes.add(s.shape());
        } else ; // error
    }
	@Override
	public void dispatchPIDSEvent(PIDSEvent event) {
		((PIDSEventDispatcher) thisParent()).dispatchPIDSEvent(event);
	}
	@Override
	public Node getChildFrom(Model<?, ?> model) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Model<?, ?> getChildFrom(Node node) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void showProperties(PShape child) {
		// TODO Auto-generated method stub

	}
}