package pids;

import fx.launcher.Launcher;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModelTest extends Launcher {
    public static void main(String[] args) {
        launchApp(ModelTest.class, args);
    }
}