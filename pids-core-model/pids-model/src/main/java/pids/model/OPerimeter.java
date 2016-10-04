package pids.model;
import pids.core.Perimeter;
import pids.core.Sector;
import pids.core.Data;
import pids.core.Anchor;
import pids.core.Camera;
import pids.core.Hooter;
import pids.core.ModelFactory;

final class OPerimeter extends OContainer<Perimeter, Data, Sector> implements Perimeter {
	public OPerimeter(OData map, String id) {
        super(map, id);
    }
    public OPerimeter(OData parent) {
        this(parent, Util.getUUId());
    }
    @Override
    final ModelFactory createHeaderFactory() {
        return new Factory.SectorFactory(this);
    }
    @SuppressWarnings("rawtypes")
	@Override
    public final Anchor[] anchors() throws Exception {
        return head().head() != null ? (Anchor[]) head().head().list() : new Anchor[0];
    }
    @Override
    public final Camera[] cameras() throws Exception {
        return head().camera() != null ? head().camera().list() : new Camera[0];
    }
    @Override
    public final Hooter[] hooters() throws Exception {
        return head().hooter() != null ? head().hooter().list() : new Hooter[0];
    }
}
