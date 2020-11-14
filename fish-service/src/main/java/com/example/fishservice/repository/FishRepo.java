package com.example.fishservice.repository;

import com.example.fishservice.entity.Fish;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FishRepo extends MongoRepository<Fish, String> {


    @Query("{'caughtByFisherManSerialNumber' : ?0}")
    List<Fish> getAllFishByFisherManSerialNumber(String serialNumber);
}
