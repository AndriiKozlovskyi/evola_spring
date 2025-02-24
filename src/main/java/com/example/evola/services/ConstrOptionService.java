package com.example.evola.services;

import com.example.evola.dtos.ConstrOptionRequestDTO;
import com.example.evola.dtos.ConstrOptionResponseDTO;
import com.example.evola.dtos.OptionRequestDTO;
import com.example.evola.dtos.OptionResponseDTO;
import com.example.evola.repositories.ConstrOptionRepository;
import com.example.evola.repositories.OptionRepository;
import com.example.evola.tables.ConstrOption;
import com.example.evola.tables.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConstrOptionService {

    private final ConstrOptionRepository constrOptionRepository;
    private final OptionRepository optionRepository;

    @Autowired
    public ConstrOptionService(ConstrOptionRepository constrOptionRepository, OptionRepository optionRepository) {
        this.constrOptionRepository = constrOptionRepository;
        this.optionRepository = optionRepository;
    }

    public List<ConstrOptionResponseDTO> getAllOptions() {
        List<ConstrOption> constrOptions = constrOptionRepository.findAll();
        return constrOptions.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
    }

    public Optional<ConstrOptionResponseDTO> getOptionById(Long id) {
        return constrOptionRepository.findById(id).map(this::convertToResponseDTO);
    }

    public ConstrOptionResponseDTO saveOption(ConstrOptionRequestDTO optionDTO) {
        ConstrOption constrOption = new ConstrOption();
        constrOption.setName(optionDTO.getName());

        ConstrOption finalConstrOption = constrOption;
        List<Option> options = null;
        if(optionDTO.getOptions() != null) {
            options = optionDTO.getOptions().stream().map(optionRequest -> {
                Option option = new Option();
                option.setValue(optionRequest.getValue());
                option.setPrice(optionRequest.getPrice());
                option.setImageSrc(optionRequest.getImageSrc());
                option.setConstrOption(finalConstrOption);
                return option;
            }).collect(Collectors.toList());
            constrOption.setOptions(options);
        }

        constrOption = constrOptionRepository.save(constrOption);
        System.out.println(constrOption);
        return convertToResponseDTO(constrOption);
    }

    public void deleteOption(Long id) {
        constrOptionRepository.deleteById(id);
    }

    public OptionResponseDTO addOptionToConstrOption(Long constrOptionId, OptionRequestDTO optionDTO) {
        Optional<ConstrOption> constrOption = constrOptionRepository.findById(constrOptionId);
        if (constrOption.isPresent()) {
            Option option = new Option();
            option.setValue(optionDTO.getValue());
            option.setPrice(optionDTO.getPrice());
            option.setImageSrc(optionDTO.getImageSrc());
            option.setConstrOption(constrOption.get());
            option = optionRepository.save(option);
            return convertToResponseDTO(option);
        }
        throw new RuntimeException("ConstrOption not found");
    }

    private ConstrOptionResponseDTO convertToResponseDTO(ConstrOption constrOption) {
        ConstrOptionResponseDTO dto = new ConstrOptionResponseDTO();
        dto.setId(constrOption.getId());
        dto.setName(constrOption.getName());
        if(constrOption.getOptions() == null) {
            return dto;
        }
        dto.setOptions(constrOption.getOptions().stream().map(this::convertToResponseDTO).collect(Collectors.toList()));
        return dto;
    }

    private OptionResponseDTO convertToResponseDTO(Option option) {
        OptionResponseDTO dto = new OptionResponseDTO();
        dto.setId(option.getId());
        dto.setValue(option.getValue());
        dto.setPrice(option.getPrice());
        dto.setImageSrc(option.getImageSrc());
        return dto;
    }
}

