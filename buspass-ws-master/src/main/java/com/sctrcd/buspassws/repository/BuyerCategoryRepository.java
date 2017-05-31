package com.sctrcd.buspassws.repository;

import com.sctrcd.buspassws.model.BuyerCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zlatan on 31.5.17..
 */
public interface BuyerCategoryRepository extends MongoRepository<BuyerCategory, String> {

    public BuyerCategory findByCode(String code);
}
