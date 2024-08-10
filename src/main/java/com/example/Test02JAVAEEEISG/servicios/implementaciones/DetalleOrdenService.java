package com.example.Test02JAVAEEEISG.servicios.implementaciones;

import com.example.Test02JAVAEEEISG.modelos.DetalleOrdenEISG;
import com.example.Test02JAVAEEEISG.modelos.OrdenEISG;
import com.example.Test02JAVAEEEISG.repositorios.IDetalleOrdenRepository;
import com.example.Test02JAVAEEEISG.servicios.interfaces.IDetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenService implements IDetalleOrdenService {
    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public Page<DetalleOrdenEISG> buscarTodosPaginados(Pageable pageable) {
        return detalleOrdenRepository.findAll(pageable);
    }

    @Override
    public List<DetalleOrdenEISG> obtenerTodos() {
        return detalleOrdenRepository.findAll();
    }

    @Override
    public Optional<DetalleOrdenEISG> buscarPorId(Long id) {
        return detalleOrdenRepository.findById(id.intValue());
    }

    @Override
    public DetalleOrdenEISG crearOEditar(DetalleOrdenEISG detalleOrdenEISG) {
        return detalleOrdenRepository.save(detalleOrdenEISG);
    }

    @Override
    public void eliminarPorId(Long id) {
        detalleOrdenRepository.deleteById(id.intValue());
    }
}
