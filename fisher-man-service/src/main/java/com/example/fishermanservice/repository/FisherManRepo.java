package com.example.fishermanservice.repository;


import com.example.fishermanservice.entity.FisherMan;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FisherManRepo extends MongoRepository<FisherMan,String> {

}
