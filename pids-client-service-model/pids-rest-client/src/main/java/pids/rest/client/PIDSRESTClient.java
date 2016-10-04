package pids.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pids.core.Data;
import pids.data.DataConverter;
import pids.data.PerimeterData;
import pids.service.AbstractService;
import pids.service.CameraService;
import pids.service.ImageService;

@Component
public class PIDSRESTClient extends AbstractService {
    @Autowired
    private ImageService bgservice;
    @Autowired
    private CameraService cameraservice;
    @Value("${pids.service.url}")
    private String REST_ROOT_URL;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public final ImageService getImageService() {
        return bgservice;
    }
    @Override
    protected final PerimeterData getData() throws Exception {
        ResponseEntity<PerimeterData> response = restTemplate.getForEntity(REST_ROOT_URL, PerimeterData.class);
        if (HttpStatus.OK == response.getStatusCode()) {
            System.out.println(response);
            return response.getBody();
        }
        return null;
    }
    @Override
    public final void save(Data data) throws Exception {
        restTemplate.postForEntity(REST_ROOT_URL, DataConverter.convertFrom(data), PerimeterData.class);
    }
    @Override
    public final void deleteAll() {
        restTemplate.delete(REST_ROOT_URL);
    }
	@Override
	public CameraService getCameraService() {
		return cameraservice;
	}
}