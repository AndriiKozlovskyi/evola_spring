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
    private boolean onSell;
    private String description;

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

    public boolean isOnSell() {
        return onSell;
    }

    public void setOnSell(boolean onSell) {
        this.onSell = onSell;
    }
}
