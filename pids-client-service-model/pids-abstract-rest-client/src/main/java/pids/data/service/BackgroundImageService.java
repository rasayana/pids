package pids.data.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URI;
import java.io.InputStream;
import java.util.Collections;
import org.springframework.core.io.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pids.service.ImageService;

@Component
public class BackgroundImageService implements ImageService {
    private final String FILE_NAME = "/imageservice/";
    @Value("${pids.service.url}")
    private String REST_ROOT_URL;
    @Override
    public String getURL() throws Exception {
        return REST_ROOT_URL + FILE_NAME;
    }
    @Autowired
    public RestTemplate restTemplate;
    @Override
    public URI save(File file) throws Exception {
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
        Resource fileResource = new FileSystemResource(file);
        parts.add("file", fileResource);
        return restTemplate.postForLocation(getURL(), parts);
    }
    @Override
    public InputStream getStream() throws Exception {
        ResponseEntity<byte[]> response = restTemplate.exchange(getURL(), HttpMethod.GET, null, byte[].class, Collections.EMPTY_MAP);
        return (response.getStatusCode() == HttpStatus.OK) ? new ByteArrayInputStream(response.getBody()) : null;
    }
}