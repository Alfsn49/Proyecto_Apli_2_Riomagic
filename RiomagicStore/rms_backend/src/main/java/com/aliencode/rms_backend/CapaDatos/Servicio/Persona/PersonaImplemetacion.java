package com.aliencode.rms_backend.CapaDatos.Servicio.Persona;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Persona;
import com.aliencode.rms_backend.CapaDatos.Dao.PersonaRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaImplemetacion implements PersonaServicio{
    @Autowired
    private PersonaRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Persona> findAll() {
        return (List<Persona>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Persona save(Persona persona){
        return repositorio.save(persona);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_persona){
        repositorio.deleteById(id_persona);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Persona findById(Integer id_persona) {
        return repositorio.findById(id_persona).orElse(null);
    }
    //BUCAR EL EMAIL
    @Override
    @Transactional(readOnly = true)
    public Persona findByEmail(String email) {
        return repositorio.findByEmail(email);
    }
}