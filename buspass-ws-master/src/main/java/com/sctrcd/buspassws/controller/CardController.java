package com.sctrcd.buspassws.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zlatan on 2.6.17..
 */
@RestController
@RequestMapping("/cardController")
public class CardController {

    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = "application/json")
    public String addItem(@RequestParam("json") List<String> json, @RequestParam("user") String userCode) {

        System.out.println(json + userCode);

        return "ok";
    }

}
