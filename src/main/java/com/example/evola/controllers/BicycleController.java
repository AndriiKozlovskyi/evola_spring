package com.example.evola.controllers;

import com.example.evola.dtos.BicycleRequest;
import com.example.evola.dtos.BicycleResponse;
import com.example.evola.services.BicycleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/bicycles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BicycleController {

    @Autowired
    private BicycleService bicycleService;

    @GetMapping
    public List<BicycleResponse> getAllBicycles() {
        return bicycleService.getAllBicycles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BicycleResponse> getBicycleById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bicycleService.getBicycleById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<?> createBicycle(
            @RequestPart("bicycle") String bicycleJson,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            BicycleRequest bicycleRequest = objectMapper.readValue(bicycleJson, BicycleRequest.class);
            return ResponseEntity.ok(bicycleService.saveBicycle(bicycleRequest, image));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format");
        }
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateBicycle(@PathVariable Long id, @RequestPart("bicycle") String bicycleJson,
                                           @RequestPart(value = "image", required = false) MultipartFile image) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            BicycleRequest bicycleRequest = objectMapper.readValue(bicycleJson, BicycleRequest.class);
            return ResponseEntity.ok(bicycleService.updateBicycle(id, bicycleRequest, image));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteBicycle(@PathVariable Long id) {
        bicycleService.deleteBicycle(id);
        return ResponseEntity.noContent().build();
    }
}

