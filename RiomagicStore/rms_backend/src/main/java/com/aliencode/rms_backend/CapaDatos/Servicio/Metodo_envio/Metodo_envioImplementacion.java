package com.aliencode.rms_backend.CapaDatos.Servicio.Metodo_envio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Metodo_envio;
import com.aliencode.rms_backend.CapaDatos.Dao.Metodo_envioRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class Metodo_envioImplementacion implements Metodo_envioServicio{
    @Autowired
    private Metodo_envioRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Metodo_envio> findAll() {
        return (List<Metodo_envio>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Metodo_envio save(Metodo_envio metodo_envio){
        return repositorio.save(metodo_envio);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_metodo_envio){
        repositorio.deleteById(id_metodo_envio);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Metodo_envio findById(Integer id_metodo_envio) {
        return repositorio.findById(id_metodo_envio).orElse(null);
    }
}