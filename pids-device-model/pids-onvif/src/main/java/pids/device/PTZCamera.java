package pids.device;

import java.net.ConnectException;
import java.util.List;
import javax.xml.soap.SOAPException;
import org.onvif.ver10.schema.FloatRange;
import org.onvif.ver10.schema.PTZPreset;

public class PTZCamera extends MediaDevice {
	public PTZCamera(String cameraAddress, String user, String password) throws ConnectException, SOAPException {
		super(cameraAddress, user, password);
	}
	public boolean isPtzOperationsSupported(String profileToken) {
		return getPTZ().isPtzOperationsSupported(profileToken);
	}
	public boolean isRelativeMoveSupported(String profileToken) {
		return getPTZ().isRelativeMoveSupported(profileToken);
	}
	public boolean isContinuosMoveSupported(String profileToken) {
		return getPTZ().isContinuosMoveSupported(profileToken);
	}
	public boolean isAbsoluteMoveSupported(String profileToken) {
		return getPTZ().isAbsoluteMoveSupported(profileToken);
	}
	public FloatRange getPanSpaces(String profileToken) {
		return getPTZ().getPanSpaces(profileToken);
	}
	public FloatRange getTiltSpaces(String profileToken) {
		return getPTZ().getTiltSpaces(profileToken);
	}
	public FloatRange getZoomSpaces(String profileToken) {
		return getPTZ().getZoomSpaces(profileToken);
	}
	public List<PTZPreset> getPresets(String profileToken) {
		return getPTZ().getPresets(profileToken);
	}
	public boolean gotoPreset(String presetToken, String profileToken) {
		return getPTZ().gotoPreset(presetToken, profileToken);
	}
	public boolean removePreset(String presetToken, String profileToken) {
		return getPTZ().removePreset(presetToken, profileToken);
	}
	public boolean relativeMove(String profileToken, float x, float y, float zoom) {
		return getPTZ().relativeMove(profileToken, x, y, zoom);
	}
	public boolean continuousMove(String profileToken, float x, float y, float zoom) {
		return getPTZ().continuousMove(profileToken, x, y, zoom);
	}
	public boolean stopMove(String profileToken) {
		return getPTZ().stopMove(profileToken);
	}
	public boolean absoluteMove(String profileToken, float x, float y, float zoom) throws SOAPException {
		return getPTZ().absoluteMove(profileToken, x, y, zoom);
	}
}