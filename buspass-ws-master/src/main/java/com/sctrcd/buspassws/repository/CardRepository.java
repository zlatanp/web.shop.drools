package com.sctrcd.buspassws.repository;

import com.sctrcd.buspassws.model.ItemCountUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zlatan on 7/8/17.
 */
public interface CardRepository extends MongoRepository<ItemCountUser, String> {

    public ItemCountUser findByUnique(String unique);
}
