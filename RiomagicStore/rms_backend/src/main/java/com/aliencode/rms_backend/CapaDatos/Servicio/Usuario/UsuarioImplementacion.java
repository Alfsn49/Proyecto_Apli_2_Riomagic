package com.aliencode.rms_backend.CapaDatos.Servicio.Usuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Usuario;
import com.aliencode.rms_backend.CapaDatos.Dao.UsuarioRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioImplementacion implements UsuarioServicio{
    @Autowired
    private UsuarioRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Usuario> findAll() {
        return (List<Usuario>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Usuario save(Usuario usuario){
        return repositorio.save(usuario);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_usuario){
        repositorio.deleteById(id_usuario);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Usuario findById(Integer id_usuario) {
        return repositorio.findById(id_usuario).orElse(null);
    }
    
}