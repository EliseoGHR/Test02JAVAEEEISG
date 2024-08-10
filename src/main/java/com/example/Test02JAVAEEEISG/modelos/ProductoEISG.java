package com.example.Test02JAVAEEEISG.modelos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
public class ProductoEISG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotBlank(message = "El nombre es requerido")
    private String nombreEISG;

    @OneToMany(mappedBy = "productoEISG", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleOrdenEISG> detalleOrdenes = new HashSet<>();

    public Set<DetalleOrdenEISG> getDetalleOrdenes() {
        return detalleOrdenes;
    }

    public void setDetalleOrdenes(Set<DetalleOrdenEISG> detalleOrdenes) {
        this.detalleOrdenes = detalleOrdenes;
    }

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
