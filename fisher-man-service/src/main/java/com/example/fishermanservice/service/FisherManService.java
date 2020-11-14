package com.example.fishermanservice.service;

import com.example.fishermanservice.entity.FisherMan;
import com.example.fishermanservice.repository.FisherManRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FisherManService {

    @Autowired
    private FisherManRepo fisherManRepo;

    public FisherMan save(FisherMan fisherMan) {
       return fisherManRepo.save(fisherMan);
        
    }

    public Optional<FisherMan> get(String fisherManId) {
        return fisherManRepo.findById(fisherManId);

    }

    public List<FisherMan> getAll() {
        return fisherManRepo.findAll();
    }

    public FisherMan update(FisherMan fisherMan) {
       FisherMan oldFisherman=  fisherManRepo.findById(fisherMan.getId()).orElse(null);
       if(oldFisherman!= null){
           oldFisherman.setName(fisherMan.getName());
           oldFisherman.setTotalFishCaught(fisherMan.getTotalFishCaught());
           oldFisherman.setTotalCash(fisherMan.getTotalCash());
           oldFisherman.setPhoneNumber(fisherMan.getPhoneNumber());
       }
       fisherManRepo.save(oldFisherman);
       return oldFisherman;
    }

    public void delete(FisherMan fisherMan) {
        fisherManRepo.delete(fisherMan);
    }
}
