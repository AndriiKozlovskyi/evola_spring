package com.example.evola.tables;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "bicycle_options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
    private String price;
    private String imageSrc;

    @ManyToOne
    @JoinColumn(name = "constr_option_id", nullable = false)
    private ConstrOption constrOption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public ConstrOption getConstrOption() {
        return constrOption;
    }

    public void setConstrOption(ConstrOption constrOption) {
        this.constrOption = constrOption;
    }
}
