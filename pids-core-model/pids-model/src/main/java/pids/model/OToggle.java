package pids.model;
import pids.core.Toggle;
import pids.core.Sector;
@SuppressWarnings("rawtypes")
abstract class OToggle<T extends Toggle> extends ONode<T> implements Toggle<T> {
    private boolean on = false;
    @Override
    public final void setOn(boolean flag) { 
        on = flag;
    }
    @Override
    public final boolean isOn() {
        return on; 
    }
    OToggle(Sector otdr, String id) { 
        super(otdr, id);
    }
}