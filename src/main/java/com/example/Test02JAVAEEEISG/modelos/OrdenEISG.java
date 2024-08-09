package com.example.Test02JAVAEEEISG.modelos;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ordenes")
public class OrdenEISG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La fecha es requerida")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEISG;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaEISG() {
        return fechaEISG;
    }

    public void setFechaEISG(LocalDate fechaEISG) {
        this.fechaEISG = fechaEISG;
    }
    private String formattedFechaEISG;

    public String getFormattedFechaEISG() {
        return formattedFechaEISG;
    }

    public void setFormattedFechaEISG(String formattedFechaEISG) {
        this.formattedFechaEISG = formattedFechaEISG;
    }

}
