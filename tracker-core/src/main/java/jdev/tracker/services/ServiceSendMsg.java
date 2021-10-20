package jdev.tracker.services;

import jdev.tracker.db.PointGPS;
import jdev.tracker.db.repo.PointGPSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

@EnableJpaRepositories("jdev.tracker.db")
@EntityScan(basePackageClasses = jdev.tracker.db.PointGPS.class)
@Service
public class ServiceSendMsg {
    private static final Logger LOG_ERRORS = LoggerFactory.getLogger("allError.TrackerCore");
    private static final Logger LOG_TRACE = LoggerFactory.getLogger("allTrace.TrackerCore");
    private List<String> list;

    @Autowired
    PointGPSRepository pointGPSRepository;
    @Autowired
    ServiceSaveMsg serviceSaveMsg;

    public List<String> sendQueueMsg() {
        BlockingQueue bq = serviceSaveMsg.getBlockingQueue();
        list = new ArrayList<>(bq);
        cleanDump();
        return list;
    }

    private void cleanDump(){
        try {
            serviceSaveMsg.getBlockingQueue().removeAll(list);
        }
        catch (Exception ex){
            LOG_ERRORS.error("Неудачная попытка очистки очереди: " + ex.getMessage());
        }
    }

    // Возвращает список маршрута по заданному номеру машины (autoId)
    public List<String> sendRoute(String autoId, String segmentLength){
        int quantityPoint = Integer.parseInt(segmentLength);
        List<PointGPS> pointGPSList;
        List<String> routeList = new ArrayList<>();
        pointGPSList = (List<PointGPS>) pointGPSRepository.findAll();

        if (pointGPSList.size() == 0) {
            LOG_TRACE.trace("NO RECORDS (sendQueueMsg.sendRoute)");
        } else {
            pointGPSList.stream()
                    .filter(pointGPS -> pointGPS.getAutoNumber().equals(autoId))
                    .limit(quantityPoint)
                    .forEach(pointGPS -> routeList.add(pointGPS.toString()));
        }

        return routeList;
    }
}
