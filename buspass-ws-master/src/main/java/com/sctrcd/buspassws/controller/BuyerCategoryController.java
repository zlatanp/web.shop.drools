package com.sctrcd.buspassws.controller;

import com.sctrcd.buspassws.model.BuyerCategory;
import com.sctrcd.buspassws.model.BuyerProfile;
import com.sctrcd.buspassws.model.User;
import com.sctrcd.buspassws.repository.BuyerCategoryRepository;
import com.sctrcd.buspassws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zlatan on 31.5.17..
 */
@RestController
@RequestMapping("/buyerCategory")
public class BuyerCategoryController {

    @Autowired
    private BuyerCategoryRepository repository;


    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public String addCategory(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("from") int from, @RequestParam("to") int to, @RequestParam("coefficient") int coefficient){

        System.out.println(code + name + from + to + coefficient);
        if(code.isEmpty() || name.isEmpty())
            return "nill";

        List<BuyerCategory> allCategories = repository.findAll();

        for (BuyerCategory c: allCategories) {
            if(c.getCode().equals(code))
                return "codeErr";

        }
        BuyerCategory b = new BuyerCategory(code, name, from, to, coefficient);

        repository.save(b);

        return "ok";
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    public List<BuyerCategory> getAll(){

        return repository.findAll();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET, produces = "application/json")
    public BuyerCategory getOne(@RequestParam("code") String code){

        return repository.findByCode(code);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public String updateCategory(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("from") int from, @RequestParam("to") int to, @RequestParam("coefficient") int coefficient){
        if(code.isEmpty() || name.isEmpty())
            return "nill";

        BuyerCategory c = repository.findByCode(code);

        //repository.delete(repository.findByCode(code));
        c.setName(name);
        c.setFrom(from);
        c.setTo(to);
        c.setCoefficient(coefficient);
        repository.save(c);

        List<User> korisnici = userRepository.findAll();

        for (User u: korisnici) {
            BuyerProfile b = u.getBuyerProfile();
            if (b != null) {
                if (b.getCategory().getCode().equals(c.getCode())) {
                    u.getBuyerProfile().setCategory(c);
                    userRepository.save(u);
                }
            }
        }
        return "ok";
    }
}
