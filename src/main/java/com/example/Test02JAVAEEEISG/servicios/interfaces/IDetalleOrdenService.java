package com.example.Test02JAVAEEEISG.servicios.interfaces;

import com.example.Test02JAVAEEEISG.modelos.DetalleOrdenEISG;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IDetalleOrdenService {
    Page<DetalleOrdenEISG> buscarTodosPaginados(Pageable pageable);

    List<DetalleOrdenEISG> obtenerTodos();

    Optional<DetalleOrdenEISG> buscarPorId(Long id);

    DetalleOrdenEISG crearOEditar(DetalleOrdenEISG detalleOrdenEISG);

    void eliminarPorId(Long id);
}
