package com.example.Test02JAVAEEEISG.modelos;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;


import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ordenes")
public class OrdenEISG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha es requerida")
    private Date fecha;

    @OneToMany(mappedBy = "ordenEISG")
    private Set<DetalleOrdenEISG> detalleOrdenes = new HashSet<>();

    public Set<DetalleOrdenEISG> getDetalleOrdenes() {
        return detalleOrdenes;
    }

    public void setDetalleOrdenes(Set<DetalleOrdenEISG> detalleOrdenes) {
        this.detalleOrdenes = detalleOrdenes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "La fecha es requerida") Date getFecha() {
        return fecha;
    }

    public void setFecha(@NotNull(message = "La fecha es requerida") Date fecha) {
        this.fecha = fecha;
    }
}
