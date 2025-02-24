package com.example.evola.services;

import com.example.evola.dtos.BicycleRequest;
import com.example.evola.dtos.BicycleResponse;
import com.example.evola.repositories.BicycleRepository;
import com.example.evola.tables.Bicycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BicycleService {

    @Autowired
    private BicycleRepository bicycleRepository;

    public BicycleResponse saveBicycle(BicycleRequest bicycleRequest, MultipartFile image) {
        Bicycle bicycle = new Bicycle();
        bicycle.setModel(bicycleRequest.getModel());
        bicycle.setBatteryCapacity(bicycleRequest.getBatteryCapacity());
        bicycle.setWheelSize(bicycleRequest.getWheelSize());
        bicycle.setPrice(bicycleRequest.getPrice());
        bicycle.setMotor(bicycleRequest.getMotor());
        bicycle.setOnSell(bicycleRequest.isOnSell());
        bicycle.setDescription(bicycleRequest.getDescription());

        try {
            if (image != null && !image.isEmpty()) {
                byte[] imageBytes = image.getBytes();
                System.out.println("Image Bytes Length: " + imageBytes.length); // Debugging
                bicycle.setImageData(imageBytes);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image", e);
        }

        Bicycle savedBicycle = bicycleRepository.save(bicycle);

        return mapBicycleToResponse(savedBicycle);
    }

    /** 🔹 Get all Bicycles with Base64 images */
    public List<BicycleResponse> getAllBicycles() {
        List<Bicycle> bicycles = bicycleRepository.findAll();

        List<BicycleResponse> responses = new ArrayList<>();
        for(Bicycle bicycle : bicycles) {
            responses.add(mapBicycleToResponse(bicycle));
        }

        return responses;
    }

    /** 🔹 Get a single Bicycle by ID */
    public BicycleResponse getBicycleById(Long id) {
        Bicycle bicycle = bicycleRepository.findById(id).orElseThrow(() -> new RuntimeException("No bicycle found with id: " + id));
        return mapBicycleToResponse(bicycle);
    }

    /** 🔹 Update Bicycle details */
    public Bicycle updateBicycle(Long id, BicycleRequest updatedBicycle, MultipartFile image) throws IOException {
        return bicycleRepository.findById(id).map(existingBicycle -> {
            existingBicycle.setModel(updatedBicycle.getModel());
            existingBicycle.setBatteryCapacity(updatedBicycle.getBatteryCapacity());
            existingBicycle.setWheelSize(updatedBicycle.getWheelSize());
            existingBicycle.setPrice(updatedBicycle.getPrice());
            existingBicycle.setMotor(updatedBicycle.getMotor());
            existingBicycle.setOnSell(updatedBicycle.isOnSell());
            existingBicycle.setDescription(updatedBicycle.getDescription());

            try {
                if (image != null && !image.isEmpty()) {
                    existingBicycle.setImageData(image.getBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to update bicycle image", e);
            }

            return bicycleRepository.save(existingBicycle);
        }).orElseThrow(() -> new RuntimeException("Bicycle not found with ID: " + id));
    }

    /** 🔹 Delete a Bicycle */
    public void deleteBicycle(Long id) {
        bicycleRepository.deleteById(id);
    }

    private BicycleResponse mapBicycleToResponse(Bicycle bicycle) {
        BicycleResponse response = new BicycleResponse();
        response.setId(bicycle.getId());
        response.setModel(bicycle.getModel());
        response.setBatteryCapacity(bicycle.getBatteryCapacity());
        response.setWheelSize(bicycle.getWheelSize());
        response.setPrice(bicycle.getPrice());
        response.setMotor(bicycle.getMotor());
        response.setDescription(bicycle.getDescription());
        response.setImageBase64(bicycle.getImageBase64());
        response.setOnSell(bicycle.isOnSell());
        return response;
    }
}
