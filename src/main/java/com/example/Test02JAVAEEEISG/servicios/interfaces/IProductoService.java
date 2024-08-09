package com.example.Test02JAVAEEEISG.servicios.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Test02JAVAEEEISG.modelos.ProductoEISG;

public interface IProductoService {
    Page<ProductoEISG> buscarTodosPaginados(Pageable pageable);

    List<ProductoEISG> obtenerTodos();

    Optional<ProductoEISG> buscarPorId(Integer id);

    ProductoEISG crearOEditar(ProductoEISG productoEISG);

    void eliminarPorId(Integer id);
}
