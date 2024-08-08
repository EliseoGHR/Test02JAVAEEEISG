package com.example.Test02JAVAEEEISG.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Test02JAVAEEEISG.modelos.ProductoEISG;

public interface IProductoRepositoy  extends JpaRepository <ProductoEISG, Integer>{

}
