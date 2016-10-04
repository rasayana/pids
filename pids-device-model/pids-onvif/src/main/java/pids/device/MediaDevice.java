package pids.device;

import java.net.ConnectException;
import javax.xml.soap.SOAPException;

public class MediaDevice extends AbstractDevice {
	public MediaDevice(String cameraAddress, String user, String password) throws ConnectException, SOAPException {
		super(cameraAddress, user, password);
	}
	public String getSnapshotUri(String profileToken) {
		return getMedia().getSnapshotUri(profileToken);
	}
	public String getRTSPStreamUri(int mediaProfileNumber) {
		return getMedia().getRTSPStreamUri(mediaProfileNumber);
	}
	public String getHTTPStreamUri(int mediaProfileNumber) {
		return getMedia().getHTTPStreamUri(mediaProfileNumber);
	}
	public String getUDPStreamUri(int mediaProfileNumber) {
		return getMedia().getUDPStreamUri(mediaProfileNumber);
	}
	public String getTCPStreamUri(int mediaProfileNumber) {
		return getMedia().getTCPStreamUri(mediaProfileNumber);
	}
}