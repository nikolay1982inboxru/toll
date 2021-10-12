package jdev.servercore;

import jdev.dto.PointDTO;

public class Main {
    public static void main(String... args) throws Exception {
        for (int i=0; i<5; i++) {
            System.out.println("Server-CORE say Hello!!!!");
            PointDTO point = new PointDTO();
            point.setLat(45);
            System.out.println(point.toJson());
            Thread.sleep(1000);
        }
    }
}
