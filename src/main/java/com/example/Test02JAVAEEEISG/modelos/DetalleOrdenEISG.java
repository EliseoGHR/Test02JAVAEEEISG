package com.example.Test02JAVAEEEISG.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "detalleOrdenes")
public class DetalleOrdenEISG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private OrdenEISG ordenEISG;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEISG productoEISG;

    @NotNull(message = "La existencia es requerida")
    private int cantidadEISG;

    @NotNull(message = "El precio es requerido")
    private double precioEISG;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdenEISG getOrdenEISG() {
        return ordenEISG;
    }

    public void setOrdenEISG(OrdenEISG ordenEISG) {
        this.ordenEISG = ordenEISG;
    }

    public ProductoEISG getProductoEISG() {
        return productoEISG;
    }

    public void setProductoEISG(ProductoEISG productoEISG) {
        this.productoEISG = productoEISG;
    }

    public int getCantidadEISG() {
        return cantidadEISG;
    }

    public void setCantidadEISG(int cantidadEISG) {
        this.cantidadEISG = cantidadEISG;
    }

    public double getPrecioEISG() {
        return precioEISG;
    }

    public void setPrecioEISG(double precioEISG) {
        this.precioEISG = precioEISG;
    }
}
