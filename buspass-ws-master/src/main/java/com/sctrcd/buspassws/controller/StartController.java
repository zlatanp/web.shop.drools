package com.sctrcd.buspassws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zlatan on 29.5.17..
 */
@Controller
public class StartController {

    @RequestMapping("/")
    public String startMethod(){
        return "index.html";
    }
}
