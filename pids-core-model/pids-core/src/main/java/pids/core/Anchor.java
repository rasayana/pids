package pids.core;

@SuppressWarnings("rawtypes")
public interface Anchor extends Toggle<Anchor> {
	DeviceInfo[] devices();
	Anchor set(DeviceInfo device);
	Anchor unSet(DeviceInfo device);
}