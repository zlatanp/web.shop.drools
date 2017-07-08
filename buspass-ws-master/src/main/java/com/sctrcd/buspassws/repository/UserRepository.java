package com.sctrcd.buspassws.repository;

import com.sctrcd.buspassws.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by zlatan on 29.5.17..
 */
public interface UserRepository extends MongoRepository<User, String> {

    //repository.save(new User(...))                                Create
    //repository.findAll()                                          Read
    public User findByUsername(String username);                    //Read specific

    //repository.save(u);                                           Update
    //repository.delete(repository.findByUsername("username1"));    Delete specific

}
