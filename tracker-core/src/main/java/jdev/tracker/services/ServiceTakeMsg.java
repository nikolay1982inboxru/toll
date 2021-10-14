package jdev.tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.BlockingQueue;

@Service
@RestController
public class ServiceTakeMsg {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/p")
    public BlockingQueue<String> takePoints(){
        return restTemplate
                .getForObject("http://localhost:8081/dto", BlockingQueue.class);
    }
}
