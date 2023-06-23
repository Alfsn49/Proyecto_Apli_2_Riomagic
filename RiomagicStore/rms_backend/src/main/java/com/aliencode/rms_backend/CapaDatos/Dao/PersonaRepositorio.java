package com.aliencode.rms_backend.CapaDatos.Dao;
import org.springframework.data.repository.CrudRepository;
import com.aliencode.rms_backend.CapaDatos.Entidades.Persona;

public interface PersonaRepositorio extends CrudRepository<Persona, Integer>{
    Persona findByEmail(String email);
    
}
