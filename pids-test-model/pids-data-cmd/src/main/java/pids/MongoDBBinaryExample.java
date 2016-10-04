package pids;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
 
public class MongoDBBinaryExample {
    private static final Logger LOG = Logger.getLogger(MongoDBBinaryExample.class.getName());
    public static void main1(String[] args) throws IOException {
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("howtodoinjava");
        //Save a image in DB
        saveImageIntoMongoDB(db);
        //Get a image from DB
        getSingleImageExample(db);
        //Get all images from DB
        listAllImages(db);
        saveToFileSystem(db);      
        //Delete images from DB
        deleteImageFromMongoDB(db);
        //Verifying if image was deleted or not
        getSingleImageExample(db);
    }
    private static void saveImageIntoMongoDB(DB db) throws IOException {
        String dbFileName = "DemoImage";
        File imageFile = new File("/home/prabs/sample2.jpg");
        GridFS gfsPhoto = new GridFS(db, "photo");
        GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
        gfsFile.setFilename(dbFileName);
        gfsFile.save();
    }
    private static void getSingleImageExample(DB db) {
        String newFileName = "DemoImage";
        GridFS gfsPhoto = new GridFS(db, "photo");
        GridFSDBFile imageForOutput = gfsPhoto.findOne(newFileName);
        LOG.info(imageForOutput.toString());
    }
    private static void listAllImages(DB db) {
        GridFS gfsPhoto = new GridFS(db, "photo");
        DBCursor cursor = gfsPhoto.getFileList();
        while (cursor.hasNext()) {
            LOG.info(cursor.next().toString());
        }
    }
    private static void saveToFileSystem(DB db) throws IOException {
        String dbFileName = "DemoImage";
        GridFS gfsPhoto = new GridFS(db, "photo");
        GridFSDBFile imageForOutput = gfsPhoto.findOne(dbFileName);
        imageForOutput.writeTo("/home/prabs/DemoImageNew.JPG");
    }
    private static void deleteImageFromMongoDB(DB db) {
        String dbFileName = "DemoImage";
        GridFS gfsPhoto = new GridFS(db, "photo");
        gfsPhoto.remove(gfsPhoto.findOne(dbFileName));
    }
}