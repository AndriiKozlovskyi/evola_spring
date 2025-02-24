package com.example.evola.dtos;

import java.util.List;

public class ConstrOptionRequestDTO {
    private String name;
    private List<OptionRequestDTO> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OptionRequestDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionRequestDTO> options) {
        this.options = options;
    }
}
