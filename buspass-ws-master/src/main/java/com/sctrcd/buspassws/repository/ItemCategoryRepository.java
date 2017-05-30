package com.sctrcd.buspassws.repository;

import com.sctrcd.buspassws.model.ItemCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zlatan on 30.5.17..
 */
public interface ItemCategoryRepository extends MongoRepository<ItemCategory, String> {

    public ItemCategory findByCode(String code);
}
