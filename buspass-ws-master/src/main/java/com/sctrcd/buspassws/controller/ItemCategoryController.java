package com.sctrcd.buspassws.controller;

import com.sctrcd.buspassws.model.ItemCategory;
import com.sctrcd.buspassws.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zlatan on 30.5.17..
 */
@RestController
@RequestMapping("/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryRepository repository;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public String addCategory(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("maxdiscount") int maxdiscount, @RequestParam("supercategory") String supercategory, @RequestParam("wholesale") String wholesale){

        System.out.println(code + name + maxdiscount + supercategory + wholesale);
        if(code.isEmpty() || name.isEmpty() || supercategory.isEmpty())
            return "nill";

        List<ItemCategory> allCategories = repository.findAll();

        for (ItemCategory c: allCategories) {
            if(c.getCode().equals(code))
                return "codeErr";

        }

        if(wholesale.equals("false")) {
            ItemCategory c = new ItemCategory(code, name, supercategory, maxdiscount, false);
            repository.save(c);
        }else{
            ItemCategory c = new ItemCategory(code, name, supercategory, maxdiscount, true);
            repository.save(c);
        }


        return "ok";
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    public List<ItemCategory> getAllCategories(){

        List<ItemCategory> c = repository.findAll();

        return c;
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET, produces = "application/json")
    public ItemCategory getOne(@RequestParam("code") String code){

        if(code.isEmpty())
            return null;

        ItemCategory c = repository.findByCode(code);

        return c;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public String updateCategory(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("maxdiscount") int maxdiscount, @RequestParam("supercategory") String supercategory,  @RequestParam("wholesale") String wholesale){
        if(code.isEmpty() || name.isEmpty() || supercategory.isEmpty())
            return "nill";

        ItemCategory c = repository.findByCode(code);


        //repository.delete(repository.findByCode(code));
        c.setName(name);
        c.setMaxDiscount(maxdiscount);
        c.setSuperCategory(supercategory);
        if(wholesale.equals("false")) {
            c.setWholesale(false);
        }else{
            c.setWholesale(true);
        }
        repository.save(c);

        return "ok";
    }
}

