package com.example.evola.dtos;

import java.util.List;

public class ConstrOptionResponseDTO {
    private Long id;
    private String name;
    private List<OptionResponseDTO> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OptionResponseDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionResponseDTO> options) {
        this.options = options;
    }
}
