package pids.model;

import pids.core.Sector;
import pids.core.Anchor;
import pids.core.Perimeter;
import pids.core.Camera;
import pids.core.Hooter;
import pids.core.ModelFactory;

@SuppressWarnings("rawtypes")
final class OSector extends OContainer<Sector, Perimeter, Anchor>  implements Sector {
	private Camera cameraHead;
    private Hooter hooterHead;
    public OSector(OPerimeter parent, String id) {
        super(parent, id);
    }
    public OSector(OPerimeter parent) {
        this(parent, Util.getUUId());
    }
    @Override
    public final Camera removeCamera(String id) throws Exception {
        return (OCamera) camera().remove(id);
    }
    @Override
    public final Camera createCamera(String id) throws Exception {
        if (camera() == null) {
            cameraHead = new Factory.CameraFactory(this).instance(id);
            cameraHead.prepare();
        } else
            return camera().add(id);
        return camera();
    }
    @Override
    public final Camera camera() throws Exception {
        return cameraHead;
    }
    @Override
    public final Hooter removeHooter(String id) throws Exception {
        return hooter().remove(id);
    }
    @Override
    public final Hooter createHooter(String id) throws Exception {
        if (hooter() == null) {
            hooterHead = new Factory.HooterFactory(this).instance(id);
            hooterHead.prepare();
        } else
            return hooter().get(id);
        return hooterHead;
    }
    @Override
    public final Hooter hooter() throws Exception {
        return hooterHead;
    }
    @Override
    final ModelFactory createHeaderFactory() {
        return new Factory.AnchorFactory(this);
    }
}