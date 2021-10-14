package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ServiceGPS {
    private static final Logger LOG_ERRORS = LoggerFactory.getLogger("allError.TrackerCore");

    @Autowired
    ServiceSaveMsg serviceSaveMsg;

    // Выполнить по расписанию
    @Scheduled(cron = "${gps.cron}")
    // Эмулирование случайных значений (широта, долгота, азимут, мгн.скорость)
    void emulateValue() {
        PointDTO pointDTO = new PointDTO();

        // Радоминг точки GPS
        pointDTO.setLon(Math.random() * 180);
        pointDTO.setLat(Math.random() * 90);
        pointDTO.setAzimuth((int)(Math.random() * 360));
        pointDTO.setInstantSpeed(Math.random() * 130);

        // Запись точки в очередь
        try {
            serviceSaveMsg.putMsg(pointDTO.toJson());
        }
        catch (JsonProcessingException JsonEx){
            LOG_ERRORS.error("Неудачная попытка сформировать JSON описание для PointDTO: " + JsonEx.getMessage());
        }

    }
}
