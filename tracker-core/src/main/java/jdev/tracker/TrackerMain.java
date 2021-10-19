package jdev.tracker;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class TrackerMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TrackerMain.class)
                .profiles("Test", "Prod")
                .run(args);
    }
}
