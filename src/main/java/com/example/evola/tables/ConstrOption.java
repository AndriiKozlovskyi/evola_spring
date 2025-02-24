package com.example.evola.tables;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "constr_option")
public class ConstrOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "constrOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;

    @Override
    public String toString() {
        return "ConstrOption{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", options=" + options +
                '}';
    }

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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}

