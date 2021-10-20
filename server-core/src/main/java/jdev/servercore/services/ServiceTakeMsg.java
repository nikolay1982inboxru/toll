package jdev.servercore.services;

import jdev.servercore.db.PointDevice;
import jdev.servercore.db.repo.PointDeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@EnableJpaRepositories("jdev.servercore.db")
@EntityScan(basePackageClasses = jdev.servercore.db.PointDevice.class)
@Service
public class ServiceTakeMsg {
    private static final Logger LOG_COORDS = LoggerFactory.getLogger("takeCoords.ServerCore");
    private static final Logger LOG_ERRORS = LoggerFactory.getLogger("allError.ServerCore");
    private static final Logger LOG_TRACE = LoggerFactory.getLogger("allTrace.ServerCore");

    private final RestTemplate restTemplate;

    public ServiceTakeMsg(@Autowired RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Autowired
    PointDeviceRepository pointDeviceRepository;

    @Scheduled(cron = "${takeFromServer.cron}")
    public List<String> takeQueueMsg(){
        final String url = "http://localhost:8081/point/";  // Адрес сервера хранения точек трекера (tracker-core)
        List<String> listOfPoints = new ArrayList<>();

        try {
            listOfPoints = restTemplate.postForObject(
                    url,
                    listOfPoints,
                    List.class);

            LOG_TRACE.trace(String.valueOf("Передано " + listOfPoints.size() + " координат."));
            listOfPoints.stream()
                    .forEach(coord -> {
                        create(System.currentTimeMillis(), 1, coord);
                        LOG_COORDS.info(coord);});

        }
        catch (HttpClientErrorException e)
        {
            LOG_ERRORS.error(e.getResponseBodyAsString());
        }
        catch(Exception e)
        {
            LOG_ERRORS.error(e.getMessage());
        }

        return listOfPoints;
    }

/*****************************************************************/
/**Работа с БД****************************************************/
/*****************************************************************/
    /**
     * Create
     */
    private PointDevice create(
                            long timePoint,
                            int idDevice,
                            String coord){
        PointDevice pointDevice = new PointDevice(
                timePoint,
                idDevice,
                coord
        );
        return pointDeviceRepository.save(pointDevice);
    }
    /**
     * Read
     */
    private void read() {
        List<PointDevice> pointDevicesList;
        pointDevicesList = (List<PointDevice>) pointDeviceRepository.findAll();

        if (pointDevicesList.size() == 0) {
            LOG_TRACE.trace("NO RECORDS");
        } else {
            pointDevicesList.stream().forEach(rocket -> LOG_TRACE.trace(rocket.toString()));
        }
    }
    /**
     * Update
     */
    private void update(PointDevice pointDevice,
                        long timePoint,
                        int idDevice,
                        String coord) {
        pointDevice = new PointDevice(
                timePoint,
                idDevice,
                coord);
        pointDeviceRepository.save(pointDevice);
    }
    /**
     * Delete
     */
    private void delete(PointDevice pointDevice) {
        pointDeviceRepository.delete(pointDevice);
    }
}
