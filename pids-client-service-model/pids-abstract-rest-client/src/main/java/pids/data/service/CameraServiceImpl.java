package pids.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pids.core.Camera;
import pids.service.CameraService;
import pids.data.DataConverter;

@Component
public class CameraServiceImpl implements CameraService {
    @Value("${pids.service.url}")
    private String REST_ROOT_URL;
    @Autowired
    private RestTemplate restTemplate;
    @Override
	public String getURL(Camera camera) throws Exception {
    	ResponseEntity<String> response = restTemplate.getForEntity(REST_ROOT_URL + "/deviceservice/", String.class, DataConverter.getItem(camera));
		return response.getBody();
	}
}