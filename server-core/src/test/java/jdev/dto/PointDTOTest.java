package jdev.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.PointDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PointDTOTest {
    private String expected = "{\"lat\":56.0,\"lon\":74.0,\"autoId\":\"o567gfd\",\"time\":1606667160082}";
    private String autoId = "o567gfd";
    private long time = 1606667160082L;

    @Test
    public void toJson() throws Exception {
        PointDTO point = new PointDTO();
        point.setLat(56);
        point.setLon(74);
        point.setAutoId("o567gfd");
        point.setTime(System.currentTimeMillis());
        assertTrue(point.toJson().contains("\"lat\":56"));
        assertTrue(point.toJson().contains("\"time\":"));
        System.out.println(point.toJson());
    }

    @Test
    public void decodeDTO() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        PointDTO point = objectMapper.readValue(expected, PointDTO.class);
        assertEquals(autoId, point.getAutoId());
        assertEquals(time, point.getTime());
    }
}
