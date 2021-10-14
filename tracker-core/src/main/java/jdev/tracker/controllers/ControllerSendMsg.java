package jdev.tracker.controllers;

import jdev.tracker.services.ServiceSaveMsg;
import jdev.tracker.services.ServiceSendMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/point")
public class ControllerSendMsg {
    @Autowired
    private ServiceSendMsg serviceSendMsg;

    @RequestMapping(method = RequestMethod.POST, path = "/")
    @ResponseBody
    public List<String> addListPoint() {
       return serviceSendMsg.sendQueueMsg();
    }
}
