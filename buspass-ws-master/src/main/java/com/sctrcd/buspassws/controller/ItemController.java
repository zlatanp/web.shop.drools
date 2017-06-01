package com.sctrcd.buspassws.controller;

import com.sctrcd.buspassws.enumeration.RecordStatus;
import com.sctrcd.buspassws.model.Item;
import com.sctrcd.buspassws.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by zlatan on 1.6.17..
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @RequestMapping(value = "/add", method = RequestMethod.GET, produces = "application/json")
    public String addItem() {

        Item i1 = new Item("111", "Intel i3", "Processors", 120, 10, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i2 = new Item("112", "Intel i5", "Processors", 200, 10, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i3 = new Item("200", "GeForce GTX 1060", "GPUs", 220, 15, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i4 = new Item("311", "WD 1TB", "HDDs", 80, 20, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i5 = new Item("321", "Samsung 850EVO", "SSDs", 120, 10, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i6 = new Item("322", "Kingston HyperX Fury", "SSDs", 90, 10, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i7 = new Item("400", "Samsung 24'", "Monitors", 300, 10, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i8 = new Item("411", "BenQ 27'", "Monitors", 330, 10, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i9 = new Item("420", "Asus ROG 17'", "Asus", 1000, 10, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i10 = new Item("421", "Asus x55", "Asus", 330, 10, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i11 = new Item("620", "Samsung Galaxy S7", "Samsung", 750, 10, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i12 = new Item("621", "Samsung Galaxy S8", "Samsung", 850, 10, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i13 = new Item("820", "iPhone 7", "Apple", 750, 10, "2017-06-01", RecordStatus.ACTIVE, false, 2);
        Item i14 = new Item("821", "iPhone 7plus", "Apple", 1250, 10, "2017-06-01", RecordStatus.ACTIVE, false, 2);

        repository.save(i1);
        repository.save(i2);
        repository.save(i3);
        repository.save(i4);
        repository.save(i5);
        repository.save(i6);
        repository.save(i7);
        repository.save(i8);
        repository.save(i9);
        repository.save(i10);
        repository.save(i11);
        repository.save(i12);
        repository.save(i13);
        repository.save(i14);


        return "ok";
    }

    @RequestMapping(value = "/getAllByCat", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Item> getAllFromCategory(@RequestParam("category") String category){

        return repository.findByCategory(category);
    }
}
