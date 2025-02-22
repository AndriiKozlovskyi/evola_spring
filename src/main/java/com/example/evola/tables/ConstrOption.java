package com.example.evola.tables;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "bicycles")
public class ConstrOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "bicycle_options", joinColumns = @JoinColumn(name = "bicycle_id"))
    private List<Option> options;
}

