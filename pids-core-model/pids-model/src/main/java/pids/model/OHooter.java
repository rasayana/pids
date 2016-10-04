package pids.model;

import pids.core.Hooter;

final class OHooter extends ODevice<Hooter> implements Hooter {
	public OHooter(OSector otdr, String id) { 
        super(otdr, id);
    }
    public OHooter(OSector otdr) {
        super(otdr, Util.getUUId());
    }
}