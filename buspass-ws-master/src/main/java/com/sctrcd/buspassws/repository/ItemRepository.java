package com.sctrcd.buspassws.repository;

import com.sctrcd.buspassws.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

/**
 * Created by zlatan on 1.6.17..
 */
public interface ItemRepository extends MongoRepository<Item, String> {

    public Item findByCode(String code);

    public ArrayList<Item> findByCategory(String category);
}
