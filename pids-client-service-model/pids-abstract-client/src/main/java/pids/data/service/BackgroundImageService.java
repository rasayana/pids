package pids.data.service;

import java.io.File;
import java.net.URI;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pids.data.repository.ImageFileRepository;
import pids.service.ImageService;

@Component
public class BackgroundImageService implements ImageService {
	@Autowired
	private ResourceLoader loader;
	private static final String IMG_URL1 = "classpath:images/sample1.png";
	private final String _getURL() throws IOException {
		return loader.getResource(IMG_URL1).getURL().toString();
	}
	@Autowired
	private ImageFileRepository repo;
	@Override
	public String getURL() throws Exception {
		return _getURL(); // Utils.byteArrayToURLString(repo.get());
				// new URL(new String(repo.get())).toString();
	}
	private static final String STATUS = "SUCCESS";
	@Override
	public URI save(File file) throws Exception {
		repo.createOrUpdate(new FileInputStream(file), "image/PNG");
		return null;
	}
	@Override
	public InputStream getStream() throws Exception {
		return repo.getStream();
	}
}