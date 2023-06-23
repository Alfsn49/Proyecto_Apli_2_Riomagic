package com.aliencode.rms_backend.CapaDatos.Dao;
import org.springframework.data.repository.CrudRepository;
import com.aliencode.rms_backend.CapaDatos.Entidades.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer>{
    
}
