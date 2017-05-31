package com.sctrcd.buspassws.repository;

import com.sctrcd.buspassws.model.ActionEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zlatan on 31.5.17..
 */
public interface ActionEventRepository extends MongoRepository<ActionEvent, String> {

    public ActionEvent findByCode(String code);
}
