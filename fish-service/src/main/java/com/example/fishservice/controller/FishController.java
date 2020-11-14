package com.example.fishservice.controller;

import com.example.fishservice.configuredRabbit.ConfigureRabbitMq;
import com.example.fishservice.entity.Fish;
import com.example.fishservice.service.FishService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/fish")
public class FishController {
    @Autowired
    private FishService fishService;

    private final RabbitTemplate rabbitTemplate;



    public FishController() {
        rabbitTemplate = new RabbitTemplate();
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Fish createFish(@RequestBody Fish fish) {
        fishService.save(fish);
        rabbitTemplate.convertAndSend(ConfigureRabbitMq.FISH_EXCHANGE,ConfigureRabbitMq.FISH_QUEUE,fish.getId());
        return fish;

    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Fish> getFishById(@PathVariable(value = "id", required = false) String fishId) {
        return fishService.get(fishId);


    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Fish> getAllFish() {
        return fishService.getAll();
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Fish updateFisher(@RequestBody Fish fish) {
        return fishService.update(fish);

    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteFish(@RequestBody Fish fish) {
        fishService.delete(fish);
    }

    @GetMapping(value = "/getAllBySerial/{serialNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Fish> getAllFishByFisherManSerialNumber(@PathVariable(value = "serialNumber", required = false) String serialNumber){
        return fishService.getAllFishByFisherManSerialNumber(serialNumber);

    }


}
