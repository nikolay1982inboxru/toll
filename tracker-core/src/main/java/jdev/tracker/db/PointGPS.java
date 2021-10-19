package jdev.tracker.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PointGPS")
public class PointGPS {

    @Id
    @Column(name = "time_point")
    Long timePoint;

    @Column(name = "latitude")
    double latitude;

    @Column(name = "longitude")
    double longitude;

    @Column(name = "azimuth")
    int azimuth;

    // Составные наименования столбцов
    // записываются через символ подчерка '_'
    // Если написать instantSpeed, т.е. через заглавные буквы,
    // то liquibase автоматически поменяет на instant_speed,
    // что приведет к ошибке создания БД.
    @Column(name = "instant_speed")
    double instantSpeed;

    @Column(name = "auto_number")
    String autoNumber;

    @Override
    public String toString() {
        return "PointGPS{" +
                ", time='" + timePoint +
                ", lat=" + latitude +
                ", lon=" + longitude +
                ", azimuth=" + azimuth +
                ", instantSpeed=" + instantSpeed +
                ", autoId='" + autoNumber +
                "'}";
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getAzimuth() {
        return azimuth;
    }
    public void setAzimuth(Integer azimuth) {
        this.azimuth = azimuth;
    }

    public double getInstantSpeed() {
        return instantSpeed;
    }
    public void setInstantSpeed(Double instantSpeed) {
        this.instantSpeed= instantSpeed;
    }

    public String getAutoNumber() {
        return autoNumber;
    }
    public void setAutoNumber(String autoNumber) {
        this.autoNumber = autoNumber;
    }

    public long getTimePoint() {
        return timePoint;
    }
    public void setTimePoint(long timePoint) {
        this.timePoint = timePoint;
    }

    public PointGPS(){ }
    public PointGPS(
            long timePoint,
            double latitude,
            double longitude,
            int azimuth,
            double instantSpeed,
            String autoNumber
    ){
        this.timePoint = timePoint;
        this.latitude = latitude;
        this.longitude = longitude;
        this.azimuth = azimuth;
        this.instantSpeed = instantSpeed;
        this.autoNumber = autoNumber;
    }

}
