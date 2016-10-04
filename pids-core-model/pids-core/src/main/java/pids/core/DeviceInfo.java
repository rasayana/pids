package pids.core;

@SuppressWarnings("rawtypes")
public interface DeviceInfo<T extends Device<T>, P extends Anchor> extends Base<P> {
	T getDevice();
}