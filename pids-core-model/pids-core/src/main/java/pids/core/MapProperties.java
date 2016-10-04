package pids.core;

import java.io.InputStream;

public interface MapProperties {
	String image();
	MapProperties image(String url, InputStream stream);
	InputStream stream();
	double getWidth();
	double getHeight();
}