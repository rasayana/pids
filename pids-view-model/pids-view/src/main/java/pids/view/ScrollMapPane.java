package pids.view;

import pids.core.Data;
import pids.core.Model;
import pids.view.core.PIDSEvent;
import pids.view.core.PContainer;
import pids.view.core.PModel;
import pids.view.core.PShape;
import pids.view.core.SetData;
import pids.view.core.PerimeterCanvas;
import pids.view.core.PIDSEventDispatcher;
import pids.view.model.MapPane;
import javafx.scene.control.ScrollPane;
import javafx.event.Event;
import javafx.scene.Node;

public class ScrollMapPane extends ScrollPane implements PContainer, SetData {
	private final PerimeterCanvas canvas = new MapPane(this);
	@Override
	public Data model() {
		return (Data) canvas.model();
	}
	private final PContainer parentContainer;
	public ScrollMapPane(PContainer parentContainer) {
        super();
        this.parentContainer = parentContainer;
        setPannable(true);
        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.NEVER);
        setHvalue(getHmin() + (getHmax() - getHmin()) / 2);
        setVvalue(getVmin() + (getVmax() - getVmin()) / 2);
    }
	public ScrollMapPane() {
        this(null);
    }
	@Override
	public PModel thisParent() {
		return parentContainer;
	}
	@Override
	public final PerimeterCanvas perimeterCanvas() {
		return canvas;
	}
	@Override
    public final void setData(Data value) throws Exception {
    	if (value != null && !value.equals(model()))
    		canvas.setData(value);
        setContent(getContentNode());
    }
	protected Node getContentNode() {
		return canvas.pidsPane();
	}
	@Override
	public void dispatchPIDSEvent(PIDSEvent e) {
		if (thisParent() != null)
			((PIDSEventDispatcher) thisParent()).dispatchPIDSEvent(e);
		else fireEvent((Event) e);
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