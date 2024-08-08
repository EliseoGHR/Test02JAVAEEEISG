package com.example.Test02JAVAEEEISG.servicios.implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Test02JAVAEEEISG.modelos.ProductoEISG;
import com.example.Test02JAVAEEEISG.repositorios.IProductoRepositoy;
import com.example.Test02JAVAEEEISG.servicios.interfaces.IProductoService;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private IProductoRepositoy productoRepository;

    @Override
    public Page<ProductoEISG> buscarTodosPaginados(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    public List<ProductoEISG> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<ProductoEISG> buscarPorId(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public ProductoEISG crearOEditar(ProductoEISG productoEISG) {
        return productoRepository.save(productoEISG);
    }

    @Override
    public void eliminarPorId(Integer id) {
        productoRepository.deleteById(id);
    }
}
