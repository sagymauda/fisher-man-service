package com.example.fishermanservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection ="fisher_men")
public class FisherMan {

    @Id
    private String id;
    private String name;
    private Integer totalFishCaught;
    private Integer totalCash;
    private String phoneNumber;
    private List<String> fishIds;



}
