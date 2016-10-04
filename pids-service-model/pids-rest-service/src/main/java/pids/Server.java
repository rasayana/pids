package pids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

@SpringBootApplication
public class Server {
    public @Bean LoggingEventListener mongoEventListener() {
        return new LoggingEventListener();
    }
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}