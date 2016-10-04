package pids.device;

import java.util.Date;
import java.util.List;
import java.net.ConnectException;
import javax.xml.soap.SOAPException;
import org.onvif.ver10.schema.Profile;
import de.onvif.soap.OnvifDevice;
import de.onvif.soap.devices.InitialDevices;
import de.onvif.soap.devices.MediaDevices;
import de.onvif.soap.devices.PtzDevices;
import de.onvif.soap.devices.ImagingDevices;

public class AbstractDevice {
	private final OnvifDevice device;
	public AbstractDevice(String cameraAddress, String user, String password) throws ConnectException, SOAPException {
		device = new OnvifDevice(cameraAddress, user, password);
	}
	public OnvifDevice onvifDevice() {
		return device;
	}
	public Date getDate() {
		return onvifDevice().getDate();
	}
	public InitialDevices getDevices() {
		return onvifDevice().getDevices();
	}
	public MediaDevices getMedia() {
		return onvifDevice().getMedia();
	}
	public PtzDevices getPTZ() {
		return onvifDevice().getPtz();
	}
	public ImagingDevices getImaging() {
		return onvifDevice().getImaging();
	}
	public List<Profile> getProfiles() {
		return getDevices().getProfiles();
	}
	public Profile getProfile(int profileNumber) {
		return getProfiles().get(profileNumber);
	}
}