package com.example.fishservice.service;

import com.example.fishservice.entity.Fish;
import com.example.fishservice.repository.FishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FishService {

    @Autowired
    FishRepo fishRepo;

    public Fish save(Fish fish) {
        return   fishRepo.save(fish);

    }

    public Optional<Fish> get(String fishId) {
        return fishRepo.findById(fishId);
    }


    public List<Fish> getAll() {
        return fishRepo.findAll();
    }

    public Fish update(Fish fish) {
        Fish oldFish = fishRepo.findById(fish.getId()).orElse(null);
        if(oldFish!= null){
            oldFish.setName(fish.getName());
            oldFish.setQuality(fish.getQuality());
            oldFish.setWeight(fish.getWeight());

        }
        fishRepo.save(fish);
        return fish;
    }

    public void delete(Fish fish) {
        fishRepo.delete(fish);
    }

    public List<Fish> getAllFishByFisherManSerialNumber(String serialNumber) {
        return   fishRepo.getAllFishByFisherManSerialNumber(serialNumber);

    }
}
