package pids.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pids.core.Data;
import pids.data.DataConverter;
import pids.data.PerimeterData;
import pids.data.service.PService;
import pids.service.AbstractService;
import pids.service.CameraService;
import pids.service.ImageService;

@Component
public final class PIDSClient extends AbstractService {
	@Autowired
	private PService service;
	@Override
	public final void deleteAll() {
		service.deleteAll();
	}
	@Override
	protected final PerimeterData getData() throws Exception {
		return service.getData();
	}
	@Override
	public final void save(Data value) throws Exception {
		service.saveData(DataConverter.convertFrom(value));
	}
	@Autowired
	private ImageService imageService;
	@Override
	public ImageService getImageService() {
		return imageService;
	}
	@Autowired
	private CameraService cameraService;
	@Override
	public CameraService getCameraService() {
		return cameraService;
	}
}