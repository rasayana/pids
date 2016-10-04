package pids.model;

import pids.core.Anchor;
import pids.core.DeviceInfo;

final class OAnchor extends OToggle<Anchor> implements Anchor {
	private final TList devices;
	public OAnchor(OSector otdr, String id) throws Exception {
        super(otdr, id);
        devices = new TList(this);
    }
    public OAnchor(OSector otdr) throws Exception {
        this(otdr, Util.getUUId());
    }
	@Override
	public DeviceInfo[] devices() {
		return devices.list();
	}
	@Override
	public Anchor set(DeviceInfo device) {
        devices.add(device);
		return this;
	}
	@Override
	public Anchor unSet(DeviceInfo device) {
		devices.remove(device);
		return this;
	}
}