package jdev.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PointDTOTest {
    private String expected = "{\"lat\":56.0,\"lon\":74.0,\"autoId\":\"o567gfd\"}";
    private String autoId = "o567gfd";

    @Test
    public void toJson() throws Exception {
        PointDTO point = new PointDTO();
        point.setLat(56);
        point.setLon(74);
        point.setAutoId("o567gfd");
        assertTrue(point.toJson().contains("\"lat\":56"));
        System.out.println(point.toJson());
    }

    @Test
    public void decodeDTO() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        PointDTO point = objectMapper.readValue(expected, PointDTO.class);
        assertEquals(autoId, point.getAutoId());
    }
}
