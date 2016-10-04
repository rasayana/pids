package pids.model;

import pids.core.Camera;

final class OCamera extends ODevice<Camera> implements Camera {
	public OCamera(OSector otdr, String id) { 
        super(otdr, id);
    }
    public OCamera(OSector otdr) { 
        this(otdr, Util.getUUId());
    }
}
