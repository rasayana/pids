package pids;
import fx.launcher.Launcher;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Monitor extends Launcher {
    public static void main(String[] args) {
        launchApp(Monitor.class, args);
    }
}