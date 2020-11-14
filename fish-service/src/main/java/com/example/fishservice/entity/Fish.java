package com.example.fishservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Document(collection ="fishes")
    public class Fish {

        @Id
        private String id;
        private String name;
        private Integer weight;
        private FishQuality quality;
        private String fisherManId;


}
