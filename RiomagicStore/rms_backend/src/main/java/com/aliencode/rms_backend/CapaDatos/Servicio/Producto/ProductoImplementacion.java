package com.aliencode.rms_backend.CapaDatos.Servicio.Producto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Producto;
import com.aliencode.rms_backend.CapaDatos.Dao.ProductoRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoImplementacion implements ProductoServicio{
    @Autowired
    private ProductoRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Producto> findAll() {
        return (List<Producto>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Producto save(Producto producto){
        return repositorio.save(producto);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_producto){
        repositorio.deleteById(id_producto);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Producto findById(Integer id_producto) {
        return repositorio.findById(id_producto).orElse(null);
    }
}