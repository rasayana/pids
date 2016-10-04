package pids.model;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import pids.core.MapProperties;
import fx.util.FXUtils;

public final class OMapProperties implements MapProperties {
	private String url;
	@Override
	public final String image() {
		return url;
	}
	private InputStream stream;
	@Override
	public final InputStream stream() {
		return stream;
	}
	private double width, height = 0;
	@Override
	public final MapProperties image(String url, InputStream stream) {
		this.url = url;
		this.stream = stream;
		BufferedImage readImage = FXUtils.readImage(url, stream);
        if (readImage != null) {
        	width = readImage.getWidth();
        	height = readImage.getHeight();
        }
		return this;
	}
	@Override
	public final double getWidth() {
		return width;
	}
	@Override
	public final double getHeight() {
		return height;
	}
}