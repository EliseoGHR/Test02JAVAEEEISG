package com.example.Test02JAVAEEEISG.repositorios;

import com.example.Test02JAVAEEEISG.modelos.DetalleOrdenEISG;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrdenEISG, Integer> {

}
