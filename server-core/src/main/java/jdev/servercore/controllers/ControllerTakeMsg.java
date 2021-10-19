package jdev.servercore.controllers;

import jdev.servercore.services.ServiceTakeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/point")
public class ControllerTakeMsg {
    @Autowired
    ServiceTakeMsg serviceTakeMsg;

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public List<String> addListPoint() throws IOException {
        return serviceTakeMsg.takeQueueMsg();
    }

}
