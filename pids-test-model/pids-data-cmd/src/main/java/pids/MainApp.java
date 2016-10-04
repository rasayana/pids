package pids;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import pids.config.TestConfig;
import pids.core.Data;
import pids.service.Service;

@SpringBootApplication
public class MainApp implements CommandLineRunner {
    public @Bean LoggingEventListener mongoEventListener() {
	return new LoggingEventListener();
    }
    @Autowired
    private Service service;
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApp.class, args);
    }
    @Autowired
    private TestConfig config;
    @Override
    public void run(String... args) throws Exception {
        service.deleteAll();
        Data data = config.getData();
        service.save(data);
        File file = new File("/home/prabs/sample1.png");
        System.out.println(service.getImageService().save(file));
        data = service.get();
        FileOutputStream outputStream = new FileOutputStream(new File("/home/prabs/sample2.png"));
        try {
	        InputStream stream = data.mapProperties().stream();
	        int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = stream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} finally {
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		TestConfig.anchorShow(data);
        System.out.println(service.getImageService());
    }
}