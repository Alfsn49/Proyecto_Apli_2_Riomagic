package com.aliencode.rms_backend.CapaDatos.Servicio.Factura;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Factura;
import com.aliencode.rms_backend.CapaDatos.Dao.FacturaRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaImplementacion implements FacturaServicio{
    @Autowired
    private FacturaRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Factura> findAll() {
        return (List<Factura>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Factura save(Factura factura){
        return repositorio.save(factura);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_factura){
        repositorio.deleteById(id_factura);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Factura findById(Integer id_factura) {
        return repositorio.findById(id_factura).orElse(null);
    }
}