package com.sctrcd.buspassws.controller;

import com.sctrcd.buspassws.model.ActionEvent;
import com.sctrcd.buspassws.repository.ActionEventRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.*;

/**
 * Created by zlatan on 31.5.17..
 */
@RestController
@RequestMapping("/actionEvent")
public class ActionEventController {

    @Autowired
    private ActionEventRepository repository;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public String addEvent(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("coefficient") int coefficient, @RequestParam("category") String category) {

        //System.out.println(code + name + from + to + coefficient + category);
        if (code.isEmpty() || name.isEmpty() || from.isEmpty() || to.isEmpty() || category.isEmpty())
            return "nill";

        List<ActionEvent> allEvents = repository.findAll();

        for (ActionEvent c : allEvents) {
            if (c.getCode().equals(code))
                return "codeErr";

        }

        JSONArray arr = new JSONArray(category);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.get(i).toString());
        }

        ActionEvent e = new ActionEvent();
        e.setCode(code);
        e.setName(name);
        e.setFrom(from);
        e.setTo(to);
        e.setDiscount(coefficient);
        e.setCategory(list);

        repository.save(e);

        return "ok";
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    public List<ActionEvent> getAll() {
        return repository.findAll();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET, produces = "application/json")
    public ActionEvent getOne(@RequestParam("code") String code) {
        return repository.findByCode(code);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json")
    public String edit(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("coefficient") int coefficient, @RequestParam("category") String category) {


        if (code.isEmpty() || name.isEmpty() || from.isEmpty() || to.isEmpty() || category.isEmpty())
            return "nill";



        JSONArray arr = new JSONArray(category);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.get(i).toString());
        }

        ActionEvent e = repository.findByCode(code);
        e.setName(name);
        e.setFrom(from);
        e.setTo(to);
        e.setDiscount(coefficient);
        e.setCategory(list);

        repository.save(e);

        return "ok";
    }

}
