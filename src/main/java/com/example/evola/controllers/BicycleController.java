package com.example.evola.controllers;

import com.example.evola.dtos.BicycleRequest;
import com.example.evola.dtos.BicycleResponse;
import com.example.evola.services.BicycleService;
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

    @PostMapping
    public ResponseEntity<?> createBicycle( @RequestPart("bicycle") BicycleRequest bicycleRequest,
                                            @RequestPart(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.ok(bicycleService.saveBicycle(bicycleRequest, image));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBicycle(@PathVariable Long id, @RequestPart("bicycle") BicycleRequest bicycleRequest,
                                           @RequestPart(value = "image", required = false) MultipartFile image) {
        try {
            return ResponseEntity.ok(bicycleService.updateBicycle(id, bicycleRequest, image));
        } catch (RuntimeException | IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBicycle(@PathVariable Long id) {
        bicycleService.deleteBicycle(id);
        return ResponseEntity.noContent().build();
    }
}

