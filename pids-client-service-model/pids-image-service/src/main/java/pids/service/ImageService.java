package pids.service;

import java.io.File;
import java.net.URI;
import java.io.InputStream;

public interface ImageService {
	InputStream getStream() throws Exception;
	URI save(File file) throws Exception;
    String getURL() throws Exception;
}