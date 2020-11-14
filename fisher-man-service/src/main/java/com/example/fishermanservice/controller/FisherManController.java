package com.example.fishermanservice.controller;


import com.example.fishermanservice.entity.FisherMan;
import com.example.fishermanservice.service.FisherManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fisherman")
public class FisherManController {

    @Autowired
    private FisherManService fisherManService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public FisherMan createFisherMan(@RequestBody FisherMan fisherMan) {
        return fisherManService.save(fisherMan);

    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<FisherMan> getFisherManById(@PathVariable(value = "id", required = false) String FisherManId) {
        return fisherManService.get(FisherManId);


    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FisherMan> getAllFisherman() {
        return fisherManService.getAll();
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public FisherMan updateFisherMan(@RequestBody FisherMan fisherMan) {
        return fisherManService.update(fisherMan);

    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteFisherMan(@RequestBody FisherMan fisherMan) {
        fisherManService.delete(fisherMan);
    }

}
