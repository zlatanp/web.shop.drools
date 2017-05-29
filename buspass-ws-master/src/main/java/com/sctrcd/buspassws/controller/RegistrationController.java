package com.sctrcd.buspassws.controller;

import com.sctrcd.buspassws.enumeration.UserType;
import com.sctrcd.buspassws.model.User;
import com.sctrcd.buspassws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by zlatan on 29.5.17..
 */
@RestController
@RequestMapping("/register")
public class RegistrationController {


    @Autowired
    private UserRepository repository;

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
