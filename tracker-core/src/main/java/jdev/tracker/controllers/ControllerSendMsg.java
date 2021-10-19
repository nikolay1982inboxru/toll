package jdev.tracker.controllers;

import jdev.tracker.services.ServiceSendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
