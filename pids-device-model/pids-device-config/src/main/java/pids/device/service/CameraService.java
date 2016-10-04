package pids.device.service;

import java.net.ConnectException;

import javax.xml.soap.SOAPException;

import pids.data.DataCamera;

public interface CameraService {
	String getRTSPURL(DataCamera camera) throws ConnectException, SOAPException;
	void gotoPreset(DataCamera camera, String preset) throws ConnectException, SOAPException;
}