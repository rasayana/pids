package pids.model;

import pids.core.Anchor;
import pids.core.Device;
import pids.core.DeviceInfo;

@SuppressWarnings("rawtypes")
abstract class ODeviceInfo<A extends DeviceInfo<T, P>, T extends Device<T>, P extends Anchor> extends OBase<A, P> implements DeviceInfo<T, P> {
	private final T device;
	ODeviceInfo(P parent, T device) {
		super(parent);
		this.device = device;
	}
	@Override
	public final T getDevice() {
		return device;
	}
}
