package com.sctrcd.buspassws.controller;

import com.sctrcd.buspassws.enumeration.UserType;
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

import java.util.Date;
import java.util.List;

/**
 * Created by zlatan on 29.5.17..
 */
@RestController
@RequestMapping("/user")
public class MainRegistrationController {


    @Autowired
    private UserRepository repository;

    @Autowired
    BuyerCategoryRepository kategorijaRepo;


    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public String register(@RequestParam("username") String username, @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("email") String email, @RequestParam("pass") String pass, @RequestParam("passRepeat") String passRepeat){

        System.out.println(username + name + surname + email + pass + passRepeat);
        if(username.isEmpty() || name.isEmpty() || surname.isEmpty() || email.isEmpty() || pass.isEmpty() || passRepeat.isEmpty())
            return "nill";

        if(!pass.equals(passRepeat))
            return "passwordmatch";

        User u = repository.findByUsername(username);
        if(u != null) {
            return "used";
        }else {
            BuyerCategory bp = null;
            List<BuyerCategory> sveKategorije = kategorijaRepo.findAll();
            for (BuyerCategory b: sveKategorije) {
                if(b.getName().equals("BRONZANI"))
                    bp = b;
            }
            BuyerProfile p = new BuyerProfile("neka adresa", 0, bp, null);
            u = new User(username, pass, name, surname, UserType.BUYER, new Date());
            u.setBuyerProfile(p);
            repository.save(u);

            return "ok";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){

        System.out.println(username + password);
        if(username.isEmpty() || password.isEmpty())
            return "nill";

        User u = repository.findByUsername(username);
        if(u == null) {
            return "nouser";
        }else{
            if(!u.getPassword().equals(password)){
                return "badpass";
            }else{
                if(u.getType().equals(UserType.BUYER))
                    return "ok";
                if(u.getType().equals(UserType.MANAGER))
                    return "manager";
                if(u.getType().equals(UserType.SELLER))
                    return "seller";
            }
        }
        return "nill";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json")
    public User profileInfo(@RequestParam("username") String username){

        if(username.isEmpty())
            return null;

        User u = repository.findByUsername(username);
        if(u==null){
            return null;
        }else{
            return u;
        }
    }


    @RequestMapping(value = "/test",method = RequestMethod.GET, produces = "application/json")
    public void test() {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new User("username1", "pass1", "Ime", "Prezime",UserType.BUYER, new Date()));
        repository.save(new User("username2", "pass2", "Ime2", "Prezime2", UserType.BUYER, new Date()));

        // fetch all customers
        System.out.println("Users found with findAll():");
        System.out.println("-------------------------------");
        for (User customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("User found with findByUsername('username1'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByUsername("username1"));

        System.out.println();

        System.out.println("Delete 'username1':");
        System.out.println("--------------------------------");
        repository.delete(repository.findByUsername("username1"));
        System.out.println("After delete findAll():");
        System.out.println("-------------------------------");
        for (User customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        repository.save(new User("username1", "pass1", "Ime", "Prezime",UserType.BUYER, new Date()));
        System.out.println("Update 'username1' to 'editovan':");
        System.out.println("--------------------------------");
        User u = repository.findByUsername("username1");
        u.setUsername("editovan");
        repository.save(u);
        System.out.println("After update findAll():");
        System.out.println("-------------------------------");
        for (User customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

    }


}
