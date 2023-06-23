package com.aliencode.rms_backend.CapaDatos.Servicio.Categoria_producto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Categoria_producto;
import com.aliencode.rms_backend.CapaDatos.Dao.Categoria_productoRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class Categoria_productoImplementacion implements Categoria_productoServicio{
    @Autowired
    private Categoria_productoRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Categoria_producto> findAll() {
        return (List<Categoria_producto>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Categoria_producto save(Categoria_producto categoria_producto){
        return repositorio.save(categoria_producto);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_categoria_producto){
        repositorio.deleteById(id_categoria_producto);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Categoria_producto findById(Integer id_categoria_producto) {
        return repositorio.findById(id_categoria_producto).orElse(null);
    }
}