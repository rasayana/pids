package pids;

import fx.launcher.Launcher;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Caliberator extends Launcher {
    public static void main(String[] args) {
        launchApp(Caliberator.class, args);
    }
}
