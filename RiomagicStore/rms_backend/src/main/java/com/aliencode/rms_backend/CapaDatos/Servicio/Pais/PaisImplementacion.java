package com.aliencode.rms_backend.CapaDatos.Servicio.Pais;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Pais;
import com.aliencode.rms_backend.CapaDatos.Dao.PaisRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class PaisImplementacion implements PaisServicio{
    @Autowired
    private PaisRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Pais> findAll() {
        return (List<Pais>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Pais save(Pais pais){
        return repositorio.save(pais);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_pais){
        repositorio.deleteById(id_pais);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Pais findById(Integer id_pais) {
        return repositorio.findById(id_pais).orElse(null);
    }
}
