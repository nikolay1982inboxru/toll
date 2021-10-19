package jdev.servercore.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class ServiceTakeMsg {
    private static final Logger LOG_COORDS = LoggerFactory.getLogger("takeCoords.ServerCore");
    private static final Logger LOG_ERRORS = LoggerFactory.getLogger("allError.ServerCore");
    private static final Logger LOG_TRACE = LoggerFactory.getLogger("allTrace.ServerCore");

    private final RestTemplate restTemplate;

    public ServiceTakeMsg(@Autowired RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

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
                    .forEach(coord -> LOG_COORDS.info(coord));

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
}
