package com.example.evola.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BicycleResponse {
    private Long id;
    private String model;
    private Integer batteryCapacity;
    private Integer wheelSize;
    private Integer price;
    private String motor;
    private String description;
    public String imageBase64;
}
