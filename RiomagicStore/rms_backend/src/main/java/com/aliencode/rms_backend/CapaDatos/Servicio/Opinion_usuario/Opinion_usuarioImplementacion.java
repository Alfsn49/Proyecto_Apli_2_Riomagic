package com.aliencode.rms_backend.CapaDatos.Servicio.Opinion_usuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Opinion_usuario;
import com.aliencode.rms_backend.CapaDatos.Dao.Opinion_usuarioRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class Opinion_usuarioImplementacion implements Opinion_usuarioServicio{
    @Autowired
    private Opinion_usuarioRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Opinion_usuario> findAll() {
        return (List<Opinion_usuario>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Opinion_usuario save(Opinion_usuario opinion_usuario){
        return repositorio.save(opinion_usuario);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_opinion_usuario){
        repositorio.deleteById(id_opinion_usuario);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Opinion_usuario findById(Integer id_opinion_usuario) {
        return repositorio.findById(id_opinion_usuario).orElse(null);
    }
}