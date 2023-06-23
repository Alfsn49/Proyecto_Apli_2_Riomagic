package com.aliencode.rms_backend.CapaDatos.Servicio.Articulo_carrito;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Articulo_carrito;
import com.aliencode.rms_backend.CapaDatos.Dao.Articulo_carritoRepositorio;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Articulo_carritoImplementacion implements Articulo_carritoService{
    @Autowired
    private Articulo_carritoRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Articulo_carrito> findAll() {
        return (List<Articulo_carrito>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Articulo_carrito save(Articulo_carrito articulo_carrito){
        return repositorio.save(articulo_carrito);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_articulo_carrito){
        repositorio.deleteById(id_articulo_carrito);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Articulo_carrito findById(Integer id_articulo_carrito) {
        return repositorio.findById(id_articulo_carrito).orElse(null);
    }
}