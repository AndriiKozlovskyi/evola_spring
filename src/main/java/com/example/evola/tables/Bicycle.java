package com.example.evola.tables;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;

@Entity
@Data
@Getter
@Setter
@Table(name = "bicycles")
public class Bicycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private Integer batteryCapacity;
    private Integer wheelSize;
    private Integer price;
    private String motor;
    private String description;
    @Lob
    @Column(columnDefinition = "BYTEA") // PostgreSQL column type for binary data
    private byte[] imageData;

    public String getImageBase64() {
        return imageData != null ? Base64.getEncoder().encodeToString(imageData) : null;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Integer getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(Integer wheelSize) {
        this.wheelSize = wheelSize;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

