package com.example.Test02JAVAEEEISG.modelos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "productos")
public class ProductoEISG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotBlank(message = "El nombre es requerido")
    private String nombreEISG;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEISG() {
        return nombreEISG;
    }

    public void setNombreEISG(String nombreEISG) {
        this.nombreEISG = nombreEISG;
    }

}
