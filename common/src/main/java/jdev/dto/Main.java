package jdev.dto;

<<<<<<< HEAD
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;

public class Main {
    public static void main(String... args) throws JsonProcessingException, InterruptedException {
=======
public class Main {
    public static void main(String... args) throws Exception {
>>>>>>> 2c65db014be42eee58eb589604ef82cece6f130e
        for (int i=0; i<5; i++) {
            System.out.println("COMMON say Hello!!!!");
            PointDTO point = new PointDTO();
            point.setLat(45);
            System.out.println(point.toJson());
            Thread.sleep(1000);
        }
    }
}
