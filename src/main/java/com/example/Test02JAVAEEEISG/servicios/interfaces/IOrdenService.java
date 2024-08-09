package com.example.Test02JAVAEEEISG.servicios.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Test02JAVAEEEISG.modelos.OrdenEISG;

public interface IOrdenService {
    Page<OrdenEISG> buscarTodosPaginados(Pageable pageable);

    List<OrdenEISG> obtenerTodos();

    Optional<OrdenEISG> buscarPorId(Long id);

    OrdenEISG crearOEditar(OrdenEISG ordenEISG);

    void eliminarPorId(Long id);
}
