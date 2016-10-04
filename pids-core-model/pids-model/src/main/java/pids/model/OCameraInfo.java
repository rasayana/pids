package pids.model;

import pids.core.Camera;
import pids.core.Anchor;
import pids.core.CameraInfo;

@SuppressWarnings("rawtypes")
public final class OCameraInfo extends ODeviceInfo<CameraInfo<Anchor>, Camera, Anchor> implements CameraInfo<Anchor> {
	private final String profileName;
	private final String presetName;
	public OCameraInfo(Anchor parent, Camera device, String profileName, String presetName) {
		super(parent, device);
		this.profileName = profileName;
		this.presetName = presetName;
	}
	@Override
	public final String  getProfileName() {
		return profileName;
	}
	@Override
	public final String getPresetName() {
		return presetName;
	}
}
