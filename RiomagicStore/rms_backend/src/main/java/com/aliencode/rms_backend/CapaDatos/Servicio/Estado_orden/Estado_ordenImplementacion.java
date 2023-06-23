package com.aliencode.rms_backend.CapaDatos.Servicio.Estado_orden;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Estado_orden;
import com.aliencode.rms_backend.CapaDatos.Dao.Estado_ordenRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class Estado_ordenImplementacion implements Estado_ordenServicio{
    @Autowired
    private Estado_ordenRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Estado_orden> findAll() {
        return (List<Estado_orden>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Estado_orden save(Estado_orden estado_orden){
        return repositorio.save(estado_orden);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_estado_orden){
        repositorio.deleteById(id_estado_orden);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Estado_orden findById(Integer id_estado_orden) {
        return repositorio.findById(id_estado_orden).orElse(null);
    }
}