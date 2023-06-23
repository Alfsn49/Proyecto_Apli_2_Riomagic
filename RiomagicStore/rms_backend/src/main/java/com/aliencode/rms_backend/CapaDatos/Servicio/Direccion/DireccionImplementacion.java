package com.aliencode.rms_backend.CapaDatos.Servicio.Direccion;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Direccion;
import com.aliencode.rms_backend.CapaDatos.Dao.DireccionRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class DireccionImplementacion implements DireccionServicio{
    @Autowired
    private DireccionRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Direccion> findAll() {
        return (List<Direccion>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Direccion save(Direccion direccion){
        return repositorio.save(direccion);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_direccion){
        repositorio.deleteById(id_direccion);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Direccion findById(Integer id_direccion) {
        return repositorio.findById(id_direccion).orElse(null);
    }
}