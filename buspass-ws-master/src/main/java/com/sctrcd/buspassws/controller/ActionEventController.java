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

        String[] fromdate = from.split("-");
        System.out.println(fromdate[0]);
        System.out.println(fromdate[1]);
        System.out.println(fromdate[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(fromdate[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(fromdate[1]) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fromdate[2]));
        Date fromDateRepresent = cal.getTime();


        String[] todate = to.split("-");

        Calendar calD = Calendar.getInstance();
        calD.set(Calendar.YEAR, Integer.parseInt(todate[0]));
        calD.set(Calendar.MONTH, Integer.parseInt(todate[1]) - 1);
        calD.set(Calendar.DAY_OF_MONTH, Integer.parseInt(todate[2]));
        Date toDateRepresent = calD.getTime();


        JSONArray arr = new JSONArray(category);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.get(i).toString());
        }

        ActionEvent e = new ActionEvent();
        e.setCode(code);
        e.setName(name);
        e.setFrom(fromDateRepresent);
        e.setTo(toDateRepresent);
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

        String[] fromdate = from.split("-");
        System.out.println(fromdate[0]);
        System.out.println(fromdate[1]);
        System.out.println(fromdate[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(fromdate[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(fromdate[1]) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fromdate[2]));
        Date fromDateRepresent = cal.getTime();


        String[] todate = to.split("-");

        Calendar calD = Calendar.getInstance();
        calD.set(Calendar.YEAR, Integer.parseInt(todate[0]));
        calD.set(Calendar.MONTH, Integer.parseInt(todate[1]) - 1);
        calD.set(Calendar.DAY_OF_MONTH, Integer.parseInt(todate[2]));
        Date toDateRepresent = calD.getTime();


        JSONArray arr = new JSONArray(category);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.get(i).toString());
        }

        ActionEvent e = repository.findByCode(code);
        e.setName(name);
        e.setFrom(fromDateRepresent);
        e.setTo(toDateRepresent);
        e.setDiscount(coefficient);
        e.setCategory(list);

        repository.save(e);

        return "ok";
    }

}
