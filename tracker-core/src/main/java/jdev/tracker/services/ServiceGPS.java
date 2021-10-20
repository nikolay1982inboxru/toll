package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.PointDTO;
import jdev.tracker.db.PointGPS;
import jdev.tracker.db.repo.PointGPSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@EnableJpaRepositories("jdev.tracker.db")
@EntityScan(basePackageClasses = jdev.tracker.db.PointGPS.class)
@Service
public class ServiceGPS {
    private static final Logger LOG_ERRORS = LoggerFactory.getLogger("allError.TrackerCore");
    private static final Logger LOG_TRACE = LoggerFactory.getLogger("allTrace.TrackerCore");
    private String[] listAuto = {"a123bc99RU", "d456ef99RU", "g789hi99RU"};

    @Autowired
    PointGPSRepository pointGPSRepository;
    @Autowired
    ServiceSaveMsg serviceSaveMsg;

    // Выполнить по расписанию
    @Scheduled(cron = "${gps.cron}")
    // Эмулирование случайных значений (широта, долгота, азимут, мгн.скорость)
    void emulateValue() {
        PointDTO pointDTO = new PointDTO();
        Random r = new Random();

        // Радоминг точки GPS
        pointDTO.setLon(Math.random() * 180);
        pointDTO.setLat(Math.random() * 90);
        pointDTO.setAzimuth((int)(Math.random() * 360));
        pointDTO.setInstantSpeed(Math.random() * 130);
        pointDTO.setAutoId(listAuto[r.nextInt(listAuto.length)]);
        pointDTO.setTime(System.currentTimeMillis());

        // Запись точки в очередь
        try {
            serviceSaveMsg.putMsg(pointDTO.toJson());
            LOG_TRACE.trace("Emulate value & save point for autoID = " + pointDTO.getAutoId());
        }
        catch (JsonProcessingException JsonEx){
            LOG_ERRORS.error("Неудачная попытка сформировать JSON описание для PointDTO: " + JsonEx.getMessage());
        }

        // Запись полученной точки в БД
        create(pointDTO.getTime(),
                pointDTO.getLat(),
                pointDTO.getLon(),
                pointDTO.getAzimuth(),
                pointDTO.getInstantSpeed(),
                pointDTO.getAutoId());
    }

/*****************************************************************/
/**Работа с БД****************************************************/
/*****************************************************************/
    /**
     * Create
     */
    private PointGPS create(long timePoint,
                            double latitude,
                            double longitude,
                            int azimuth,
                            double instantSpeed,
                            String autoNumber){
        PointGPS pointGPS = new PointGPS(
                timePoint,
                latitude,
                longitude,
                azimuth,
                instantSpeed,
                autoNumber
                );
        return pointGPSRepository.save(pointGPS);
    }
    /**
     * Read
     */
    private void read() {
        List<PointGPS> pointGPSList;
        pointGPSList = (List<PointGPS>) pointGPSRepository.findAll();

        if (pointGPSList.size() == 0) {
            LOG_TRACE.trace("NO RECORDS");
        } else {
            pointGPSList.stream().forEach(rocket -> LOG_TRACE.trace(rocket.toString()));
        }
    }
    /**
     * Update
     */
    private void update(PointGPS pointGPS,
                        long timePoint,
                        double latitude,
                        double longitude,
                        int azimuth,
                        double instantSpeed,
                        String autoNumber) {
        pointGPS = new PointGPS(
                timePoint,
                latitude,
                longitude,
                azimuth,
                instantSpeed,
                autoNumber
        );
        pointGPSRepository.save(pointGPS);
    }
    /**
     * Delete
     */
    private void delete(PointGPS pointGPS) {
        pointGPSRepository.delete(pointGPS);
    }
}
