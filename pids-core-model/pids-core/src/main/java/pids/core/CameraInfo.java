package pids.core;

@SuppressWarnings("rawtypes")
public interface CameraInfo<T extends Anchor> extends DeviceInfo<Camera, T> {
	String getProfileName();
	String getPresetName();
}
