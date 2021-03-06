package com.sctrcd.buspassws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sctrcd.buspassws.model.BusPass;
import com.sctrcd.buspassws.model.Person;
import com.sctrcd.buspassws.service.DroolsService;

@RestController
public class BusPassController {

    private static Logger log = LoggerFactory.getLogger(BusPassController.class);

    private final DroolsService droolsService;

    @Autowired
    public BusPassController(DroolsService droolsService) {
        this.droolsService = droolsService;
    }

    @RequestMapping(value = "/buspass", 
            method = RequestMethod.GET, produces = "application/json")
    public BusPass getQuestions(
            @RequestParam(required = true) String name,
            @RequestParam(required = true) int age) {

        Person person = new Person(name, age);

        log.debug("Bus pass request received for: " + person);
        
        //BusPass busPass = droolsService.getBusPass(person);

        //return busPass;

        return null;
    }

}
