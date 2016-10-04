package pids.model;

import pids.core.Data;
import pids.core.Perimeter;
import pids.core.ModelFactory;
import pids.core.MapProperties;
import java.io.Serializable;

final class OData extends OContainer<Data, Data, Perimeter> implements Data, Serializable {
	private static final long serialVersionUID = -3585421984143397678L;
	public OData(String id) {
        super(null, id);
    }
    public OData() {
        this(Util.getUUId());
    }
    private MapProperties mapProperties = null;
    @Override
    public final Data mapProperties(MapProperties value) {
    	mapProperties = value;
        return this;
    }
    @Override
    public final MapProperties mapProperties() {
       return mapProperties;
    }
    private boolean monitoring = false;
    @Override
    public final void setMonitoring(boolean flag) {
        monitoring = flag;
    }
    @Override
    public final boolean isMonitoring() {
        return monitoring;
    }
    @Override
    final ModelFactory createHeaderFactory() {
        return new Factory.PerimeterFactory(this);
    }
}