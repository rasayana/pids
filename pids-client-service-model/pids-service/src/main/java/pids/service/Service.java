package pids.service;

import pids.core.Data;

public interface Service  {
	Data get() throws Exception;
	void save(Data data) throws Exception;
	void deleteAll();
	ImageService getImageService();
	CameraService getCameraService();
}