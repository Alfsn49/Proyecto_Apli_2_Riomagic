package com.aliencode.rms_backend.CapaDatos.Servicio.Rol;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Rol;
import com.aliencode.rms_backend.CapaDatos.Dao.RolRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class RolImplementacion implements RolServicio{
    @Autowired
    private RolRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Rol> findAll() {
        return (List<Rol>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Rol save(Rol rol){
        return repositorio.save(rol);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_rol){
        repositorio.deleteById(id_rol);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Rol findById(Integer id_rol) {
        return repositorio.findById(id_rol).orElse(null);
    }
}