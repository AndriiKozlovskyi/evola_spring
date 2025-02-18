package com.example.evola.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
public class BicycleRequest {
    private String model;
    private Integer batteryCapacity;
    private Integer wheelSize;
    private Integer price;
    private String motor;
    private String description;
    private MultipartFile image; // Handle file upload
}
