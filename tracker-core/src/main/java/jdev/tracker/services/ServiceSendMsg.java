package jdev.tracker.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@Service
public class ServiceSendMsg {
    private static final Logger LOG_ERRORS = LoggerFactory.getLogger("allError.TrackerCore");
    private List<String> list;

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
}
