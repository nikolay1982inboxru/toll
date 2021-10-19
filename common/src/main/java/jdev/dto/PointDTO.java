package jdev.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PointDTO {
    private double lat;
    private double lon;
    private int azimuth;
    private double instantSpeed;
    private String autoId;
    private long time;

    public PointDTO(){
        lat = -1.0;
        lon = -1.0;
        azimuth = -1;
        instantSpeed = -1.0;
        autoId = "null";
        time = -1L;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    @Override
    public String toString() {
        return "PointDTO{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", azimuth=" + azimuth +
                ", instantSpeed=" + instantSpeed +
                ", autoId='" + autoId + '\'' +
                '}';
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public int getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(int azimuth) {
        this.azimuth = azimuth;
    }

    public double getInstantSpeed() {
        return instantSpeed;
    }

    public void setInstantSpeed(double instantSpeed) {
        this.instantSpeed = instantSpeed;
    }
}
