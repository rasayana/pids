package pids.data.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import net.sf.jmimemagic.Magic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pids.data.dao.FileDAO;

@Slf4j
@Repository
public class ImageFileRepository {
	private final FileDAO dao;
	@Autowired
	public ImageFileRepository(FileDAO imgDAO) {
		dao = imgDAO;
	}
	public String getMimeType(InputStream stream) throws Exception {
		byte[] buf = null;
		try { buf = new byte[stream.available()];
		    while (stream.read(buf) != -1) { }
		} catch (Exception e) {
		    System.out.println("Got exception while is -> bytearr conversion: " + e);
		}
	    return Magic.getMagicMatch(buf, false).getMimeType();
	}
	public void createOrUpdate(InputStream ipS, String contentType) throws Exception {
        if (contentType.startsWith("image/")) {
            String name = "Test.png";
            Optional<GridFSDBFile> opt = dao.getByFilename(name);
            if (opt.isPresent()) {
                dao.delete(name);
            }
            DBObject metaData = new BasicDBObject();
            metaData.put("name", name);
            metaData.put("content-type", contentType);
            dao.store(ipS, name, contentType, metaData);
	    } // else log.info("It's not an image");
	}
	public Optional<GridFSDBFile> getFile() throws Exception {
		List<GridFSDBFile> list = dao.findAll();
		GridFSDBFile result = null;
		if (list.size() > 0)
			result = list.get(0);
		return Optional.ofNullable(result);
	}
	public byte[] get() throws Exception {
		byte[] imageData = null;
		Optional<GridFSDBFile> optionalCreated = getFile();
		if (optionalCreated.isPresent()) {
			GridFSDBFile created = optionalCreated.get();
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    created.writeTo(baos);
		    imageData = baos.toByteArray();
		}
		return imageData;
	}
	public InputStream getStream() throws Exception {
		Optional<GridFSDBFile> optionalCreated = getFile();
		if (optionalCreated.isPresent()) {
			GridFSDBFile created = optionalCreated.get();
			return created.getInputStream();
		}
		return null;
	}
}