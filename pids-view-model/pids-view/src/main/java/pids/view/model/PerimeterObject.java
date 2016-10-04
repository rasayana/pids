package pids.view.model;

import java.util.Map;
import java.util.Iterator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.adapter.JavaBeanDoublePropertyBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import pids.core.Device;
import pids.core.Node;
import pids.core.Anchor;
import pids.core.Camera;
import pids.core.Hooter;
import pids.core.Model;
import pids.core.Perimeter;
import pids.core.Sector;
import pids.view.core.PIDSEvent;
import pids.view.core.PIDSEventDispatcher;
import pids.view.core.PerimeterCanvas;
import pids.view.core.PModel;
import pids.view.core.PPerimeter;
import pids.view.core.PSector;
import pids.view.core.PShape;
import pids.view.core.PerimeterAttribute;
import pids.view.core.SectorAttribute;

final class PerimeterObject implements PPerimeter {
	private final PerimeterAttribute attribute;
	private final PerimeterCanvas canvas;
	private final DoubleProperty lineStrokeWidth;
	private final DoubleProperty radiusProp;
	private final DoubleProperty nodestrokeWidth;
    private JavaBeanDoublePropertyBuilder builder() {
    	return JavaBeanDoublePropertyBuilder.create().bean(getAttribute()).beanClass(PerimeterAttribute.class);
    }
	PerimeterObject(PerimeterAttribute attribute, PerimeterCanvas canvas) throws Exception {
		this.canvas = canvas;
		this.attribute = attribute;
		lineStrokeWidth = builder().name("LineStrokeWidth").build();
		radiusProp = builder().name("Radius").build();
		nodestrokeWidth = builder().name("NodeStrokeWidth").build();
		buildIt();
	}
	@Override
	public final DoubleProperty strokeWidth() {
		return lineStrokeWidth;
	}
	@Override
	public final DoubleProperty radiusProperty() {
		return radiusProp;
	}
	@Override
	public final DoubleProperty nodeStrokeWidth() {
		return nodestrokeWidth;
	}
	@Override
	public final PerimeterAttribute getAttribute() {
		return attribute;
	}
	@Override
	public final Perimeter model() {
		return getAttribute().getPerimeter();
	}
	@Override
	public final PModel thisParent() {
		return canvas;
	}
	@Override
	public final PerimeterCanvas perimeterCanvas() {
		return (PerimeterCanvas) thisParent();
	}
	private ObservableMap<Sector, PSector> sectors = FXCollections.observableHashMap();
	private boolean flag = false;
	private Color anchorColor() {
		return flag ? Constants.ANCHOR_COLOR : Constants.ANCHOR_COLOR_1;
	}
	private Color lineColor() {
		return flag ? Constants.LINE_COLOR : Constants.LINE_COLOR_1;
	}
	private SectorAttribute getAttribute(Sector s) {
		SectorAttribute result = new SectorAttrib(s);
		result.setNodeColor(anchorColor().deriveColor(1, 1, 1, 0.8));
		result.setLineColor(lineColor().deriveColor(1,1,1,1));
		result.setNodeStrokeColor(Constants.ANCHOR_STROKE_COLOR.deriveColor(1,1,1,1));
		result.setCrossOverColor(Constants.LINE_CROSSOVER_COLOR.deriveColor(1,1,1,1));
		result.setIntrusionColor(Constants.LINE_INTRUSION_COLOR.deriveColor(1,1,1,1));
		result.setCameraColor(Constants.CAMERA_COLOR.deriveColor(1, 1, 1, 0.8));
		result.setHooterColor(Constants.HOOTER_COLOR.deriveColor(1, 1, 1, 0.8));
		flag = !flag;
		return result;
	}
	@Override
	public final PSector add(Sector s) throws Exception {
		PSector result = get(s);
		if(result == null) {
			result = new SectorObject(this, getAttribute(s));
			sectors.put(s, result);
		}
		return result;
	}
	@Override
	public final PSector get(Sector s) {
		return sectors.get(s);
	}
	@Override
	public final ObservableMap<Sector, PSector> list() {
		return sectors;
	}
	@Override
	public final ObservableList<Shape> getList() {
		ObservableList<Shape> result = FXCollections.observableArrayList();
		Iterator<Map.Entry<Sector, PSector>> iterator =  sectors.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Sector, PSector> mapEntry = (Map.Entry<Sector, PSector>) iterator.next();
			PSector sector = mapEntry.getValue();
			result.addAll(sector.getList());
		}
		return result;
	}
	private void buildIt() throws Exception {
		getList().clear();
        addNodes(Anchor.class);
        addNodes(Camera.class);
        addNodes(Hooter.class);
    	if(getList().size() > 0)
    		perimeterCanvas().addPNodes(getList());
	}
	private PSector getSector(Node<?> n) throws Exception {
		PSector result = get((Sector) n.parent());
		if (result == null)
			result = add((Sector) n.parent());
		return result;
	}
    private void addNodes(Class<?> c) throws Exception {
        Sector s = model().head();
        if (s != null) {
            boolean isDevice = Device.class.isAssignableFrom(c);
            Node<?> h = isDevice ? c.isAssignableFrom(Camera.class) ? s.camera() : s.hooter() : s.head();
            if (h != null) {
                Node<?> n = h;
                PSector sector = getSector(n);
                NodeProperty<?> h1 = (NodeProperty<?>) Util.getShapeProperty(n, sector), p1 = h1, p2 = null;
                do {
                    if (p1 != null) {
                        sector.addShape(Util.getShape(p1));
                        if (!isDevice && p2 != null)
                        	sector.addShape(Util.getShape(p2, p1, sector));
                        if (!isDevice)
                            p2 = p1;
                    }
                    n = (Node<?>) n.next();
                    sector = getSector(n);
                    p1 = !n.equals(h) ? (NodeProperty<?>) Util.getShapeProperty(n, sector) : h1;
                } while (!h.equals(n));
                if (!isDevice && p1 != null && p2 != null && !p1.getNode().equals(p2.getNode()))
                	sector.addShape(Util.getShape(p2, p1, sector));
            }
        }
    }
	@Override
	public void dispatchPIDSEvent(PIDSEvent event) {
		((PIDSEventDispatcher) thisParent()).dispatchPIDSEvent(event);
	}
	@Override
	public javafx.scene.Node getChildFrom(Model<?, ?> model) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Model<?, ?> getChildFrom(javafx.scene.Node node) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void showProperties(PShape child) {
		// TODO Auto-generated method stub

	}
}