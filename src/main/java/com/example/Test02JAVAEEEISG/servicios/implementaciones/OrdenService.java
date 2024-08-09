package com.example.Test02JAVAEEEISG.servicios.implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Test02JAVAEEEISG.modelos.OrdenEISG;
import com.example.Test02JAVAEEEISG.repositorios.IOrdenRepository;
import com.example.Test02JAVAEEEISG.servicios.interfaces.IOrdenService;

@Service
public class OrdenService implements IOrdenService{
    @Autowired
    private IOrdenRepository ordenRepository;

    @Override
    public Page<OrdenEISG> buscarTodosPaginados(Pageable pageable) {
        return ordenRepository.findAll(pageable);
    }

    @Override
    public List<OrdenEISG> obtenerTodos() {
        return ordenRepository.findAll();
    }

    @Override
    public Optional<OrdenEISG> buscarPorId(Long id) {
        return ordenRepository.findById(id.intValue());
    }

    @Override
    public OrdenEISG crearOEditar(OrdenEISG ordenEISG) {
        return ordenRepository.save(ordenEISG);
    }

    @Override
    public void eliminarPorId(Long id) {
        ordenRepository.deleteById(id.intValue());
    }
}
