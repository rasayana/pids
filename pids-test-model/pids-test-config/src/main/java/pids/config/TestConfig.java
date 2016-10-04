package pids.config;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.net.URLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pids.core.Anchor;
import pids.core.Camera;
import pids.core.Hooter;
import pids.core.Data;
import pids.core.Perimeter;
//import java.net.URL;
//import pids.util.Utils;
import pids.launcher.DataService;
import pids.model.Builder;
import pids.model.OMapProperties;

@Component
@SuppressWarnings("rawtypes")
public class TestConfig extends DataService {
	@Autowired
	private ResourceLoader loader;
	private static final String IMG_URL1 = "classpath:images/sample1.png";
	private static final String IMG_URL2 = "classpath:images/sample2.jpg";
	public final Resource get(String url) {
		return loader.getResource(url);
	}
	public final File getFile(String url) throws Exception {
		return get(url).getFile();
	}
	public final String getURL(String url) throws Exception {
		System.out.println(url);
		return get(url).getURL().toString();
	}
	public final InputStream getSampleStream(String url) throws Exception {

		URLConnection connection = (URLConnection) get(url).getURL().openConnection();
		InputStream iSReader = connection.getInputStream();
		BufferedImage bufferedImage = ImageIO.read(iSReader);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
	    ImageIO.write(bufferedImage, "png", os);
	    return new ByteArrayInputStream(os.toByteArray());

	    // Turn the resource into a File object
//		File dir = new File(dir_url.toURI());
//		return Utils.getFileStream(dir);
//		return get(url).getInputStream();
	}
	public final File getSample1() throws Exception {
		return getFile(IMG_URL1);
	}
	public final String getSampleURL1() throws Exception {
		return getURL(IMG_URL1);
	}
	public final InputStream getSampleStream1() throws Exception {
		return getSampleStream(IMG_URL1);
	}
	public final File getSample2() throws Exception {
		return getFile(IMG_URL2);
	}
	public final String getSampleURL2() throws Exception {
		return getURL(IMG_URL2);
	}
	public final InputStream getSampleStream2() throws Exception {
		return getSampleStream(IMG_URL2);
	}
	private static void info(Object value) {
		System.out.println(value.toString());
    }
	private static void nodeShow(Perimeter  p) throws Exception {
		info(p);
		Anchor h = p.head().head();
		Anchor n = h;
		do {
			info(n);
			n = (Anchor) n.next();
		} while (!h.equals(n));
		info("************************ ");
		info("cameras");
		Camera C = p.head().camera();
		Camera d = C;
		do {
			info(d);
			d = d.next();
		} while (!C.equals(d));
		info("************************ ");
		info("hooters");
		Hooter a = p.head().hooter();
		Hooter b = a;
		do {
			info(b);
			b = b.next();
		} while (!a.equals(b));
		info("************************ ");
	}
	public static void anchorShow(Data  d) throws Exception {
		for (Perimeter p: d.head().list())
           nodeShow(p);
	}
	private static Data getSampleData(Data data, String url, InputStream stream) {
		OMapProperties prop = new OMapProperties();
		prop.image(url, stream);
		data.mapProperties(prop);
   		return data;
	}
	public Data getSampleData1(boolean  withStream) throws Exception {
		String url = withStream ? null : getSampleURL1();
		InputStream stream = withStream ? getSampleStream1() : null;
		return getSampleData(Builder.getSampleData(1, 7, 50, 5, 5), url, stream);
	}
	public Data getSampleData2(boolean  withStream) throws Exception {
		String url = withStream ? null : getSampleURL2();
		InputStream stream = withStream ? getSampleStream2() : null;
		return getSampleData(Builder.getSampleData(1, 3, 2, 1, 1), url, stream);
	}
	public void samppleShow() throws Exception {
		anchorShow(readData());
	}
	@Override
	protected Data readData() throws Exception {
		return getSampleData2(false);
	}
}