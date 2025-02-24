package com.example.evola.controllers;

import com.example.evola.dtos.ConstrOptionRequestDTO;
import com.example.evola.dtos.ConstrOptionResponseDTO;
import com.example.evola.dtos.OptionRequestDTO;
import com.example.evola.dtos.OptionResponseDTO;
import com.example.evola.services.ConstrOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/constr-options")
@CrossOrigin("*")
public class ConstrOptionController {

    private final ConstrOptionService service;

    @Autowired
    public ConstrOptionController(ConstrOptionService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConstrOptionResponseDTO> getAllOptions() {
        return service.getAllOptions();
    }

    @GetMapping("/{id}")
    public Optional<ConstrOptionResponseDTO> getOptionById(@PathVariable Long id) {
        return service.getOptionById(id);
    }

    @PostMapping("/admin")
    public ConstrOptionResponseDTO createOption(@RequestBody ConstrOptionRequestDTO optionDTO) {
        return service.saveOption(optionDTO);
    }

    @DeleteMapping("/admin/{id}")
    public void deleteOption(@PathVariable Long id) {
        service.deleteOption(id);
    }

    @PostMapping("/admin/{constrOptionId}/options")
    public OptionResponseDTO addOptionToConstrOption(@PathVariable Long constrOptionId, @RequestBody OptionRequestDTO optionDTO) {
        return service.addOptionToConstrOption(constrOptionId, optionDTO);
    }
}
