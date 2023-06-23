package com.aliencode.rms_backend.CapaDatos.Servicio.Orden_compra;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Orden_compra;
import com.aliencode.rms_backend.CapaDatos.Dao.Orden_compraRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class Orden_compraImplementacion implements Orden_compraServicio{
    @Autowired
    private Orden_compraRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Orden_compra> findAll() {
        return (List<Orden_compra>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Orden_compra save(Orden_compra orden_compra){
        return repositorio.save(orden_compra);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_orden_compra){
        repositorio.deleteById(id_orden_compra);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Orden_compra findById(Integer id_orden_compra) {
        return repositorio.findById(id_orden_compra).orElse(null);
    }
}