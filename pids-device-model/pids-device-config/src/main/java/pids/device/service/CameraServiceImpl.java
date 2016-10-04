package pids.device.service;

import java.net.ConnectException;
import javax.xml.soap.SOAPException;
import java.util.List;
import org.onvif.ver10.schema.PTZPreset;
import org.springframework.stereotype.Service;
import pids.data.DataCamera;
import pids.device.PTZCamera;

@Service
public class CameraServiceImpl implements CameraService {
	@Override
	public String getRTSPURL(DataCamera camera) throws ConnectException, SOAPException {
		return camera != null ? new PTZCamera(camera.getIp(), camera.getUser(), camera.getPassword()).getRTSPStreamUri(0) : null;
	}
	@Override
	public void gotoPreset(DataCamera camera, String preset) throws ConnectException, SOAPException {
		if (camera != null) {
			PTZCamera c = new PTZCamera(camera.getIp(), camera.getUser(), camera.getPassword());
			String mediaProfile = c.getProfiles().get(0).getName();
//			List<PTZPreset> presets = c.getPresets(mediaProfile);
			c.gotoPreset(preset, mediaProfile);
		}
	}
}